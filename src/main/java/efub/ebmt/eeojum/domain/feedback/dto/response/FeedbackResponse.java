package efub.ebmt.eeojum.domain.feedback.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackResponse {
    private Long feedbackId;
    private String content;

    public FeedbackResponse(Long feedbackId, String content) {
        this.feedbackId = feedbackId;
        this.content = content;
    }
}
