package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.Experience;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.lang.reflect.Executable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ExperienceRequest {
    //private Long resumeId;
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
