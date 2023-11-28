package efub.ebmt.eeojum.domain.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {
    private String name;
    private String email;
    private String pw;
    private Boolean isMentor;
}
