package efub.ebmt.eeojum.domain.member.controller;

import efub.ebmt.eeojum.domain.member.oauth.GoogleOAuth;
import efub.ebmt.eeojum.domain.member.oauth.KakaoOAuth;
import efub.ebmt.eeojum.domain.member.service.OAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
@Slf4j
@Tag(name = "OAuth 관련 API", description = "OAuth 로그인 기능을 제공합니다.")
public class OAuthController {

    private final OAuthService oAuthService;
    private final KakaoOAuth kakaoOAuth;
    private final GoogleOAuth googleOAuth;

    // 카카오 로그인
//    @GetMapping("/kakao")
//    @ResponseStatus(value = HttpStatus.OK)
//    @Operation(summary = "카카오 로그인 URL을 반환합니다.")
//    public void getKakaoAuthUrl(HttpServletResponse response) throws IOException {
//        response.sendRedirect(kakaoOAuth.responseUrl());
//    }

//    @GetMapping("/signin/kakao")
//    @ResponseStatus(value = HttpStatus.OK)
//    @Operation(summary = "카카오 로그인 API입니다.")
//    public ResponseEntity<OAuthResponseDto> kakaoSignIn(
//            @RequestParam(name = "code") String code) throws IOException {
//        return oAuthService.kakaoSignIn(code);
//    }

    // 구글 로그인
//    @GetMapping("/google")
//    @ResponseStatus(value = HttpStatus.OK)
//    @Operation(summary = "구글 로그인 URL을 반환합니다.")
//    public void getGoogleAuthUrl(HttpServletResponse response) throws Exception {
//        response.sendRedirect(googleOAuth.getOauthRedirectURL());
//    }

//    @GetMapping("/signin/google")
//    @ResponseStatus(value = HttpStatus.OK)
//    @Operation(summary = "구글 로그인 API입니다.")
//    public ResponseEntity<OAuthResponseDto> googleSignIn(
//            @RequestParam(name = "code") String code) throws IOException {
//        // URL 디코딩
//        String decodedCode = URLDecoder.decode(code, StandardCharsets.UTF_8.toString());
//        return oAuthService.googleSignIn(decodedCode);
//    }
}
