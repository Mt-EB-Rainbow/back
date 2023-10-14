package efub.ebmt.eeojum.Member.service;

import efub.ebmt.eeojum.Member.domain.Information;
import efub.ebmt.eeojum.Member.domain.Member;
import efub.ebmt.eeojum.Member.domain.RefreshToken;
import efub.ebmt.eeojum.Member.dto.InformationRequestDto;
import efub.ebmt.eeojum.Member.dto.SignInResponseDto;
import efub.ebmt.eeojum.Member.repository.InformationRepository;
import efub.ebmt.eeojum.Member.repository.MemberRepository;
import efub.ebmt.eeojum.global.config.TokenProvider;
import efub.ebmt.eeojum.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final InformationRepository informationRepository;
    private final RefreshTokenService refreshTokenService;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public Member findMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("email" + email + "은 존재하지 않습니다!"));
    }

    // 회원가입
    public String signUp(String name, String email, String pw, Date birth, String nickname) {
        if (memberRepository.existsByEmail(email)) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .name(name)
                .email(email)
                .pw(encoder.encode(pw))
                .birth(birth)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        return "회원 가입이 완료되었습니다.";
    }

    // 로그인
    public SignInResponseDto signIn(String email, String pw) {
        // 존재하지 않는 이메일
        Optional<Member> foundMember = memberRepository.findMemberByEmail(email);

        if (!foundMember.isPresent()) {
            throw new EntityNotFoundException("존재하지 않는 이메일입니다.");
        }

        // 비밀번호 검증
        Member member = foundMember.get();
        if (!encoder.matches(pw, member.getPw())) {
            throw new RuntimeException("잘못된 비밀번호입니다!");
        }

        // 로그인 성공
        // Token 생성
        String accessToken = tokenProvider.createAccessToken(member.getMemberId());
        String refreshToken = tokenProvider.createRefreshToken(member.getMemberId());

        // Token을 DB에 저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setValue(refreshToken);
        refreshTokenEntity.setMember(member);
        refreshTokenService.addRefreshToken(refreshTokenEntity);

        // Token 반환
        return SignInResponseDto.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public void addInformation(Long memberId, InformationRequestDto infoDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));

        Information information = Information.builder()
                .address(infoDto.getAddress())
                .careerBreak(infoDto.getCareerBreak())
                .prgStatus(infoDto.isPrgStatus())
                .desiredSalary(infoDto.getDesiredSalary())
                .member(member)
                .build();

        informationRepository.save(information);
    }

//    public SignInResponseDto requestRefresh(String refreshToken) {
//        // 유효성 검사
//        RefreshToken foundRefreshToken = refreshTokenService.findRefreshToken(refreshToken);
//
//        // Refresh Token에 들어있는 email 값 조회
//        Claims claims = JwtUtil.parseRefreshToken(foundRefreshToken.getValue(), refreshKey);
//        String email = claims.get("email", String.class);
//        System.out.println("Email found in RefreshToken: " + email);
//
//        // 회원 조회
//        Member member = findMemberByEmail(email);
//
//        // 새 Access Token 생성
//        String accessToken = JwtUtil.createAccessToken(member.getEmail(), accessKey, expiredTime);
//
//        // 새 Access Token과 기존 Refresh Token을 DTO에 담아 반환
//        return SignInResponseDto
//                .builder()
//                .memberId(member.getMemberId())
//                .email(member.getEmail())
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
}
