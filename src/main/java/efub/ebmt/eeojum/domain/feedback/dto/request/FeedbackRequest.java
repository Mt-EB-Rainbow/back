package efub.ebmt.eeojum.domain.feedback.dto.request;

import efub.ebmt.eeojum.domain.feedback.domain.Feedback;
import efub.ebmt.eeojum.domain.resume.domain.Resume;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedbackRequest {
    private Long memberId;
    private Long resumeId;
    private String overall;
    private String title;
    private String education;
    private String experience;
    private String language;
    private String awards;

    public Feedback of(){
        return Feedback.builder()
                .memberId(memberId)
                .resumeId(resumeId)
                .overall(overall)
                .title(title)
                .education(education)
                .experience(experience)
                .language(language)
                .awards(awards)
                .build();
    }
}
