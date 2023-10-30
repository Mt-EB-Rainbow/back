package efub.ebmt.eeojum.domain.resume.dto.request;

import efub.ebmt.eeojum.domain.resume.domain.Award;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AwardRequest {
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
