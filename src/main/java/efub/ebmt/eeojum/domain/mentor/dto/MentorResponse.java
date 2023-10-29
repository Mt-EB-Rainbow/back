package efub.ebmt.eeojum.domain.mentor.dto;

import efub.ebmt.eeojum.domain.mentor.domain.Mentor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MentorResponse {
    private String nickname;
    private String introduction;
    private String company;
    private String jobCategory;
    private String phoneNumber;
    private String email;
    private Long kakaoUserid;

    public MentorResponse(Mentor mentor){
        this.nickname = mentor.getNickname();
        this.introduction = mentor.getIntroduction();
        this.company = mentor.getCompany();
        this.jobCategory = mentor.getJobCategory();
        this.phoneNumber = mentor.getPhoneNumber();
        this.email = mentor.getEmail();
        this.kakaoUserid = mentor.getKakaoUserid();
    }
}
