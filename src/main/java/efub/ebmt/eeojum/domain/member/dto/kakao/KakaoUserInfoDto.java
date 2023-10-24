package efub.ebmt.eeojum.domain.member.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KakaoUserInfoDto {
    private Long id;
    private String connected_at;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public class KakaoAccount {
        private String email;
        private Boolean email_needs_agreement;
        private Boolean has_email;
        private Boolean is_email_valid;
        private Boolean is_email_verified;
    }
}
