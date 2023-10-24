package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.Award;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AwardsRequest {
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String activity;
    private String content;

    public Award of(Long resumeId){
        return Award.builder()
                .resumeId(resumeId)
                .startDate(startDate)
                .finishDate(finishDate)
                .activity(activity)
                .content(content)
                .build();
    }
}
