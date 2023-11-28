package efub.ebmt.eeojum.domain.feedback.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackUpdateRequest {
    private String overall;
    private String title;
    private String education;
    private String experience;
    private String language;
    private String awards;
}
