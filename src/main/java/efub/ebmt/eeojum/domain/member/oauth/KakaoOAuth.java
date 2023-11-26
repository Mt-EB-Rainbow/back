package efub.ebmt.eeojum.domain.member.oauth;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
import efub.ebmt.eeojum.domain.member.dto.kakao.KakaoOAuthTokenDto;
import efub.ebmt.eeojum.domain.member.dto.kakao.KakaoUserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoOAuth {

    private final String KAKAO_TOKEN_REQUEST_URL = "https://kauth.kakao.com/oauth/token";

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirecUri;

    public String responseUrl() {
        String kakaoSignInUrl = "https://kauth.kakao.com/oauth/authorize?client_id=" + kakaoClientId +
                "&redirect_uri=" + kakaoRedirecUri + "&response_type=code";
        return kakaoSignInUrl;
    }

    public ResponseEntity<String> requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headersAccess = new HttpHeaders();
        headersAccess.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", kakaoRedirecUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(params, headersAccess);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(KAKAO_TOKEN_REQUEST_URL,
                kakaoRequest, String.class);
        return responseEntity;
    }

//    public KakaoOAuthTokenDto getAccessToken(ResponseEntity<String> response) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        KakaoOAuthTokenDto kakaoOAuthTokenDto = objectMapper.readValue(response.getBody(), KakaoOAuthTokenDto.class);
//        return kakaoOAuthTokenDto;
//    }

    public ResponseEntity<String> requestUserInfo(KakaoOAuthTokenDto oAuthToken) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.add("Authorization", "Bearer " + oAuthToken.getAccess_token());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET, request, String.class);
        return response;
    }

//    public KakaoUserInfoDto getUserInfo(ResponseEntity<String> response) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        KakaoUserInfoDto kakaoUserInfoDto = objectMapper.readValue(response.getBody(), KakaoUserInfoDto.class);
//        return kakaoUserInfoDto;
//    }

}