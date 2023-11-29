package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.Education;
import efub.ebmt.eeojum.domain.resume.domain.EducationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EducationResponse {
    private Long educationId;
    private LocalDate startDate;
    private LocalDate finishDate;
    private EducationStatus educationStatus;
    private String name;
    private String major;
    private String degree;

    public EducationResponse(Education education){
        this.educationId = education.getEducationId();
        this.startDate = education.getStartDate();
        this.finishDate = education.getFinishDate();
        this.educationStatus = education.getEducationStatus();
        this.name = education.getName();
        this.major = education.getMajor();
        this.degree = education.getDegree();
    }
}
