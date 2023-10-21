package efub.ebmt.eeojum.domain.Member.controller;

import efub.ebmt.eeojum.domain.Member.dto.kakao.OAuthResponseDto;
import efub.ebmt.eeojum.domain.Member.oauth.GoogleOAuth;
import efub.ebmt.eeojum.domain.Member.oauth.KakaoOAuth;
import efub.ebmt.eeojum.domain.Member.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void getKakakoAuthUrl(HttpServletResponse response) throws IOException {
        response.sendRedirect(kakaoOAuth.responseUrl());
    }

    @GetMapping("/signin/kakao")
    public ResponseEntity<OAuthResponseDto> kakaoSignIn(
            @RequestParam(name = "code") String code) throws IOException {
        return oAuthService.kakaoSignIn(code);
    }
}
