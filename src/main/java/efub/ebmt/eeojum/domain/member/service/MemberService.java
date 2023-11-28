package efub.ebmt.eeojum.domain.member.service;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.member.domain.RefreshToken;
import efub.ebmt.eeojum.domain.member.dto.response.SignInResponseDto;
import efub.ebmt.eeojum.domain.member.dto.SignUpRequestDto;
import efub.ebmt.eeojum.domain.member.repository.MemberRepository;
import efub.ebmt.eeojum.global.config.TokenProvider;
import efub.ebmt.eeojum.global.exception.CustomException;
import efub.ebmt.eeojum.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RefreshTokenService refreshTokenService;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    // 회원가입
    public String signUp(SignUpRequestDto requestDto) {
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_EXISTS);
        }

        Member member = Member.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .pw(encoder.encode(requestDto.getPw()))
                .isMentor(requestDto.getIsMentor())
                .build();

        memberRepository.save(member);
        return "회원 가입이 완료되었습니다.";
    }

    // 로그인
    public SignInResponseDto signIn(String email, String pw) {
        // 존재하지 않는 이메일
        if(!memberRepository.existsByEmail(email)){
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }

        Member member = memberRepository.findByEmail(email);
        // 비밀번호 검증
        if (!encoder.matches(pw, member.getPw())) {
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
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

    @Transactional(readOnly = true)
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 멤버입니다. ID : " + memberId));
    }

    public Boolean mentorCheck(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 멤버입니다. ID : " + memberId)).getIsMentor();
    }
}
