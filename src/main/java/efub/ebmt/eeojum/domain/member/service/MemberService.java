package efub.ebmt.eeojum.domain.member.service;

import efub.ebmt.eeojum.domain.member.domain.Information;
import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.member.domain.RefreshToken;
import efub.ebmt.eeojum.domain.member.dto.InformationRequestDto;
import efub.ebmt.eeojum.domain.member.dto.SignInResponseDto;
import efub.ebmt.eeojum.domain.member.repository.InformationRepository;
import efub.ebmt.eeojum.domain.member.repository.MemberRepository;
import efub.ebmt.eeojum.global.config.TokenProvider;
import lombok.RequiredArgsConstructor;
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
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다. ID : " + memberId));

        Information information = Information.builder()
                .address(infoDto.getAddress())
                .careerBreak(infoDto.getCareerBreak())
                .prgStatus(infoDto.isPrgStatus())
                .desiredSalary(infoDto.getDesiredSalary())
                .member(member)
                .build();

        informationRepository.save(information);
    }

    @Transactional(readOnly = true)
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 멤버입니다. ID : " + memberId));
    }
}
