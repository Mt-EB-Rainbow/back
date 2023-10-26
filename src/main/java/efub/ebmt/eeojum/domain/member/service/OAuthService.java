package efub.ebmt.eeojum.domain.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.member.dto.google.GoogleOAuthTokenDto;
import efub.ebmt.eeojum.domain.member.dto.google.GoogleUserInfoDto;
import efub.ebmt.eeojum.domain.member.dto.kakao.KakaoOAuthTokenDto;
import efub.ebmt.eeojum.domain.member.dto.OAuthResponseDto;
import efub.ebmt.eeojum.domain.member.dto.kakao.KakaoUserInfoDto;
import efub.ebmt.eeojum.domain.member.oauth.GoogleOAuth;
import efub.ebmt.eeojum.domain.member.oauth.KakaoOAuth;
import efub.ebmt.eeojum.domain.member.repository.MemberRepository;
import efub.ebmt.eeojum.global.config.OAuthPlatform;
import efub.ebmt.eeojum.global.config.RedisDao;
import efub.ebmt.eeojum.global.config.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService {

    private final List<OAuthSignInService> oAuthSignInService;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final KakaoOAuth kakaoOAuth;
    private final GoogleOAuth googleOAuth;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisDao redisDao;
    UsernamePasswordAuthenticationToken authenticationToken = null;


    private ResponseEntity<OAuthResponseDto> SignInByOAuth(String email, OAuthPlatform platform) {

        if (platform.equals(OAuthPlatform.KAKAO)) {
            authenticationToken = new UsernamePasswordAuthenticationToken(email, "kakao");
        } else {
            authenticationToken = new UsernamePasswordAuthenticationToken(email, "google");
        }

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // memberId 검색
        Optional<Member> memberOptional = memberRepository.findMemberByEmail(email);
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("멤버 정보를 찾을 수 없습니다!");
        }
        Long memberId = memberOptional.get().getMemberId();

        // memberId 기반으로 토큰 생성
        String atk = tokenProvider.createAccessToken(memberId);
        String rtk = tokenProvider.createRefreshToken(memberId);

        redisDao.setValues(email, rtk, Duration.ofDays(14));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + atk);

        return new ResponseEntity<>(OAuthResponseDto.response(
                email, atk, rtk
        ), HttpStatus.OK);
    }

    // 카카오 로그인 서비스
    @Transactional
    public ResponseEntity<OAuthResponseDto> kakaoSignIn(String code) throws IOException {
        KakaoUserInfoDto kakaoUser = getKakaoUserInfoDto(code);
        String email = kakaoUser.getKakaoAccount().getEmail();

        // 첫 로그인시 사용자 정보를 보내줌
        if (!memberRepository.existsByEmail(email)) {
            return new ResponseEntity<>(OAuthResponseDto.response(
                    email, null, null
            ), HttpStatus.OK);
        }
        // 이메일이 존재할시 로그인
        return SignInByOAuth(email, OAuthPlatform.KAKAO);
    }

    // 구글 로그인 서비스
    @Transactional
    public ResponseEntity<OAuthResponseDto> googleSignIn(String code) throws IOException {
        GoogleUserInfoDto googleUser = getGoogleUserInfoDto(code);
        String email = googleUser.getEmail();
        String name = googleUser.getName();

        // 첫 로그인시 사용자 정보를 보내줌
        if (!memberRepository.existsByEmail(email)) {
            return new ResponseEntity<>(OAuthResponseDto.response(
                    email, null, null
            ), HttpStatus.OK);
        }
        // 이메일이 존재할시 로그인
        return SignInByOAuth(email, OAuthPlatform.GOOGLE);
    }

    // 소셜 로그인 서비스
    @Transactional
//    public SignInResponseDto signInByOAuth(String code, OAuthPlatform platform) {
//        // 요청된 로그인 플랫폼 확인 후 소셜 로그인 진행
//        Optional<Member> memberEntityOptional = getOptionalSocialUserEntity(code, platform);
//        if (memberEntityOptional.isEmpty()) {
//            throw new RuntimeException("소셜 로그인에 실패했습니다!");
//        }
//        Member memberEntity = memberEntityOptional.get();
//
//        // 현재 서비스 내 회원인지 검증 및 save
//        Member member = saveOrUpdate(memberEntity);
//
//        // 서비스 JWT 토큰 발급
//        String accessToken = tokenProvider.createAccessToken(member.getMemberId());
//        String refreshToken = tokenProvider.createRefreshToken(member.getMemberId());
//
//        return SignInResponseDto.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build();
//    }

    // 카카오 유저 정보 가져오기
    private KakaoUserInfoDto getKakaoUserInfoDto(String code) throws JsonProcessingException {
        ResponseEntity<String> accessTokenResponse = kakaoOAuth.requestAccessToken(code);
        KakaoOAuthTokenDto oAuthToken = kakaoOAuth.getAccessToken(accessTokenResponse);
        ResponseEntity<String> userInfoResponse = kakaoOAuth.requestUserInfo(oAuthToken);
        KakaoUserInfoDto kakaoUser = kakaoOAuth.getUserInfo(userInfoResponse);
        return kakaoUser;
    }

    // 소셜 로그인 서비스
//    private Optional<Member> getOptionalSocialUserEntity(String code, OAuthPlatform platform) {
//        for (OAuthSignInService oAuth2LoginService : oAuthSignInService) {
//            if (oAuth2LoginService.supports().equals(platform)) {
//                return Optional.of(oAuth2LoginService.toEntityMember(code, platform));
//            }
//        }
//        return Optional.empty();
//    }

//    private Member saveOrUpdate(Member memberEntity) {
//        Member member = memberRepository.findMemberByEmail(memberEntity.getEmail())
//                .map(entity -> entity.update(memberEntity.getName()))
//                .orElse(toMemberEntity(memberEntity));
//
//        return memberRepository.save(member);
//    }

//    private Member toMemberEntity(Member member) {
//        return member.builder()
//                .email(member.getEmail())
//                .name(member.getName())
//                .pw(member.getPw())
//                .nickname(member.getNickname())
//                .birth(member.getBirth())
//                .isMentor(member.isMentor())
//                .build();
//    }

    private GoogleUserInfoDto getGoogleUserInfoDto(String code) throws JsonProcessingException {
        ResponseEntity<String> accessTokenResponse = googleOAuth.requestAccessToken(code);
        GoogleOAuthTokenDto oAuthToken = googleOAuth.getAccessToken(accessTokenResponse);
        ResponseEntity<String> userInfoResponse = googleOAuth.requestUserInfo(oAuthToken);
        GoogleUserInfoDto googleUser = googleOAuth.getUserInfo(userInfoResponse);
        return googleUser;
    }
}
