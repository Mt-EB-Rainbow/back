package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.Award;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AwardResponse {
    private Long awardId;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String activity;
    private String content;

    public AwardResponse(Award award){
        this.awardId = award.getAwardsId();
        this.startDate = award.getStartDate();
        this.finishDate = award.getFinishDate();
        this.activity = award.getActivity();
        this.content = award.getContent();
    }
}
