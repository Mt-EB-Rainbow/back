package efub.ebmt.eeojum.domain.member.dto.response;

import efub.ebmt.eeojum.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class MemberInfoResponseDto {
    private Long memberId;
    private String email;
    private String name;
    private boolean isMentor;

    public MemberInfoResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.isMentor = member.getIsMentor();
    }
}
