package efub.ebmt.eeojum.domain.mentor.dto;

import efub.ebmt.eeojum.domain.mentor.domain.Mentor;
import efub.ebmt.eeojum.domain.resume.domain.Resume;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MentorRequest {
    private String nickname;
    private String introduction;
    private String company;
    private String jobCategory;
    private String phoneNumber;
    private String email;
    private Long kakaoUserid;

    public Mentor of(){
        return Mentor.builder()
                .nickname(nickname)
                .introduction(introduction)
                .company(company)
                .jobCategory(jobCategory)
                .phoneNumber(phoneNumber)
                .email(email)
                .kakaoUserid(kakaoUserid)
                .build();
    }
}
