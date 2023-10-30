package efub.ebmt.eeojum.domain.resume.dto.request;

import efub.ebmt.eeojum.domain.resume.domain.Experience;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExperienceRequest {
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private Boolean isPresent;
    private String department;
    private String position;

    public Experience of(Long resumeId){
        return Experience.builder()
                .resumeId(resumeId)
                .startDate(startDate)
                .finishDate(finishDate)
                .isPresent(isPresent)
                .department(department)
                .position(position)
                .build();
    }
}
