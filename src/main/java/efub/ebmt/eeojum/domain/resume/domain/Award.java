package efub.ebmt.eeojum.domain.resume.domain;

import efub.ebmt.eeojum.domain.resume.dto.request.AwardRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long awardsId;

    @Column(nullable = false)
    private Long resumeId;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column
    private LocalDateTime finishDate;

    @Column
    private String activity;

    @Column
    private String content;

    @Builder
    public Award(Long resumeId, LocalDateTime startDate, LocalDateTime finishDate, String activity, String content){
        this.resumeId = resumeId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.activity = activity;
        this.content = content;
    }

    public Award update(AwardRequest awardRequest){
        this.startDate = awardRequest.getStartDate();
        this.finishDate = awardRequest.getFinishDate();
        this.activity = awardRequest.getActivity();
        this.content = awardRequest.getContent();
        return this;
    }

}
