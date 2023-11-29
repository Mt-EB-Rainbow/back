package efub.ebmt.eeojum.domain.feedback.dto.response;

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
    private String mentorName;
    private String content;
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
    private LocalDateTime createdAt;

    public FeedbackResponse(Feedback feedback, String mentorName) {
        this.feedbackId = feedback.getFeedbackId();
        this.mentorName = mentorName;
        this.content = feedback.getContent();
        this.createdAt = feedback.getCreatedAt();
    }
}
