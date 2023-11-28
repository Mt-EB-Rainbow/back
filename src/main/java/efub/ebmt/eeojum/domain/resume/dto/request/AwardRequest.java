package efub.ebmt.eeojum.domain.resume.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import efub.ebmt.eeojum.domain.resume.domain.Award;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AwardRequest {
    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy년 MM월 dd일")
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
