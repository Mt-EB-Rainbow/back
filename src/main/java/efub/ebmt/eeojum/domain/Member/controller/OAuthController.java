package efub.ebmt.eeojum.domain.Member.controller;

import efub.ebmt.eeojum.domain.Member.dto.OAuthResponseDto;
import efub.ebmt.eeojum.domain.Member.oauth.GoogleOAuth;
import efub.ebmt.eeojum.domain.Member.oauth.KakaoOAuth;
import efub.ebmt.eeojum.domain.Member.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
@Slf4j
public class OAuthController {

    private final OAuthService oAuthService;
    private final KakaoOAuth kakaoOAuth;
    private final GoogleOAuth googleOAuth;

    // 카카오 로그인
    @GetMapping("/kakao")
    public void getKakaoAuthUrl(HttpServletResponse response) throws IOException {
        response.sendRedirect(kakaoOAuth.responseUrl());
    }

    @GetMapping("/signin/kakao")
    public ResponseEntity<OAuthResponseDto> kakaoSignIn(
            @RequestParam(name = "code") String code) throws IOException {
        return oAuthService.kakaoSignIn(code);
    }

    // 구글 로그인
    @GetMapping("/google")
    public void getGoogleAuthUrl(HttpServletResponse response) throws Exception {
        response.sendRedirect(googleOAuth.getOauthRedirectURL());
    }

    @GetMapping("/signin/google")
    public ResponseEntity<OAuthResponseDto> googleSignIn(
            @RequestParam(name = "code") String code) throws IOException {
        // URL 디코딩
        String decodedCode = URLDecoder.decode(code, StandardCharsets.UTF_8.toString());
        return oAuthService.googleSignIn(decodedCode);
    }
}
