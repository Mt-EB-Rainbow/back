package efub.ebmt.eeojum.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequestDto {
    private String email;
    private String pw;
}
