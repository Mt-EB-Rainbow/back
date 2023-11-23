package efub.ebmt.eeojum.domain.member.dto.request;

import lombok.Getter;

import java.util.Date;

@Getter
public class SignUpRequestDto {
    private String name;
    private String email;
    private String pw;
    private String nickname;
    private Date birth;
}
