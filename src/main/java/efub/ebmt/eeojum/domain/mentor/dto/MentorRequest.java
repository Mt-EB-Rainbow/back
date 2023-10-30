package efub.ebmt.eeojum.domain.mentor.dto;

import efub.ebmt.eeojum.domain.mentor.domain.Mentor;
import efub.ebmt.eeojum.domain.resume.domain.Resume;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MentorRequest {
    private Long memberId;
    private String nickname;
    private String introduction;
    private String company;
    private String jobCategory;
    private LocalDateTime hiredDate;
    private String phoneNumber;
    private String email;
    private Long kakaoUserid;

    public Mentor of(){
        return Mentor.builder()
                .memberId(memberId)
                .nickname(nickname)
                .introduction(introduction)
                .company(company)
                .jobCategory(jobCategory)
                .hiredDate(hiredDate)
                .phoneNumber(phoneNumber)
                .email(email)
                .kakaoUserid(kakaoUserid)
                .build();
    }
}
