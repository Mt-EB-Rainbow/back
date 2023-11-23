package efub.ebmt.eeojum.domain.member.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;

    public JwtResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

