package efub.ebmt.eeojum.domain.Member.dto.kakao;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OAuthResponseDto implements Serializable {
    private final String email;
    private final String atk;
    private final String rtk;

    public static OAuthResponseDto response(String email, String atk, String rtk) {
        return OAuthResponseDto.builder()
                .email(email)
                .atk(atk)
                .rtk(rtk)
                .build();
    }
}
