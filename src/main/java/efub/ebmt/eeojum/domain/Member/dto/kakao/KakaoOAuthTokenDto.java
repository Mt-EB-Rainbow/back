package efub.ebmt.eeojum.domain.member.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KakaoOAuthTokenDto {
    private String access_token;
    private String refresh_token;
    private Integer refresh_token_expires_in;
    private Integer expires_in;
    private String scope;
    private String token_type;
    private String id_token;
    private KakaoUserInfoDto.KakaoAccount kakaoAccount;
}
