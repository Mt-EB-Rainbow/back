package efub.ebmt.eeojum.domain.member.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KakaoInfoResponse {
    private Long id;
    private String connected_at;
    private KakaoAccount kakaoAccount;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class kakaoAccount {
        private String email;
        private Boolean has_email;
        private Boolean is_email_valid;
        private Boolean is_email_verified;
    }
}
