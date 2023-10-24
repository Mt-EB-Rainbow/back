package efub.ebmt.eeojum.domain.member.controller;

import efub.ebmt.eeojum.domain.member.dto.*;
//import efub.ebmt.eeojum.domain.member.oauth.KakaoOAuth;
import efub.ebmt.eeojum.domain.member.service.MemberService;
import efub.ebmt.eeojum.domain.member.service.RefreshTokenService;
import efub.ebmt.eeojum.domain.member.dto.RefreshTokenRequestDto;
import efub.ebmt.eeojum.domain.member.dto.SignInRequestDto;
import efub.ebmt.eeojum.domain.member.dto.SignInResponseDto;
import efub.ebmt.eeojum.domain.member.dto.SignUpRequestDto;
import efub.ebmt.eeojum.global.config.TokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "유저 관련 API", description = "회원가입, 로그인/로그아웃, 토큰 재발급 기능을 제공합니다.")
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    //private final KakaoOAuth kakaoOAuth;

    // 회원가입
    @PostMapping("/signup")
    @Operation(summary = "회원가입 API입니다. JWT를 발급합니다.")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto requestDto) {
        String responseMessage = memberService.signUp(requestDto.getName(), requestDto.getEmail(), requestDto.getPw(), requestDto.getBirth(), requestDto.getNickname());
        return ResponseEntity.ok().body(responseMessage);
    }

    // 로그인
    @PostMapping("/signin")
    @Operation(summary = "로그인 API입니다. Jwt를 발급합니다.")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto requestDto) {
        SignInResponseDto responseDto = memberService.signIn(requestDto.getEmail(), requestDto.getPw());
        return ResponseEntity.ok().body(responseDto);
    }

    // 로그아웃
    // Body로 전달된 RefreshToken을 DB에서 삭제
    @DeleteMapping("/signout")
    @Operation(summary = "로그아웃 API입니다.")
    public String signOut(@RequestBody RefreshTokenRequestDto requestDto) {
        refreshTokenService.deleteRefreshToken(requestDto.getRefreshToken());
        return "로그아웃 완료";
    }

    // RefreshToken을 이용해 새 AccessToken을 발급받는 요청
    // 프론트에서 유효한 RefreshToken을 보냈다면, 새 AccessToken 값과 기존 RefreshToken 값을 담은 DTO를 응답
    @PostMapping("/refreshtoken")
    @Operation(summary = "토큰을 재발급하는 API입니다. RefreshToken을 이용해 새 AccessToken을 발급합니다.")
    public String requestRefresh(@RequestBody RefreshTokenRequestDto refreshTokenDto) {
        return tokenProvider.refreshAccessToken(refreshTokenDto.getRefreshToken());
    }
}
