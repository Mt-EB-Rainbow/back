package efub.ebmt.eeojum.domain.feedback.dto.response;

import efub.ebmt.eeojum.domain.feedback.domain.Feedback;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackResponse {
    private Long memberId;
    private Long resumeId;
    private String overall;
    private String title;
    private String education;
    private String experience;
    private String language;
    private String awards;

    public FeedbackResponse(Feedback feedback){
        this.memberId = feedback.getMemberId();
        this.resumeId = feedback.getResumeId();
        this.overall = feedback.getOverall();
        this.title = feedback.getTitle();
        this.education = feedback.getEducation();
        this.experience = feedback.getExperience();
        this.language = feedback.getLanguage();
        this.awards = feedback.getAwards();
    }
}
