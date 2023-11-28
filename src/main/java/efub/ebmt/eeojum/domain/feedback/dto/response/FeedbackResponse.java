package efub.ebmt.eeojum.domain.feedback.dto.response;

<<<<<<< HEAD
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
=======
import com.fasterxml.jackson.annotation.JsonFormat;
import efub.ebmt.eeojum.domain.feedback.domain.Feedback;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FeedbackResponse {
    private Long feedbackId;
    private String content;
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
    private LocalDateTime createdAt;

    public FeedbackResponse(Feedback feedback) {
        this.feedbackId = feedback.getFeedbackId();
        this.content = feedback.getContent();
        this.createdAt = feedback.getCreatedAt();
>>>>>>> cbdc4d5ddf7ce1e18e79499bf1b2a5ac8f904a93
    }
}
