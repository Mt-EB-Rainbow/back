package efub.ebmt.eeojum.domain.feedback.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FeedbacksResponse {
    private List<FeedbackResponse> feedbacks;

    public FeedbacksResponse(List<FeedbackResponse> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
