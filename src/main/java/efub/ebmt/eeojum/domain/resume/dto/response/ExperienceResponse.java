package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.Experience;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ExperienceResponse {
    private Long experienceId;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Boolean isPresent;
    private String department;
    private String position;

    public ExperienceResponse(Experience experience){
        this.experienceId = experience.getExperienceId();
        this.startDate = experience.getStartDate();
        this.finishDate = experience.getFinishDate();
        this.isPresent = experience.getIsPresent();
        this.department = experience.getDepartment();
        this.position = experience.getPosition();
    }
}
