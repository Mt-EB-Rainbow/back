package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.Education;
import efub.ebmt.eeojum.domain.resume.domain.EducationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EducationRequest {
    //private Long resumeId;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private EducationStatus educationStatus;
    private String name;
    private String major;
    private String degree;

    public Education of(Long resumeId){
        return Education.builder()
                .resumeId(resumeId)
                .startDate(startDate)
                .finishDate(finishDate)
                .educationStatus(educationStatus)
                .name(name)
                .major(major)
                .degree(degree)
                .build();
    }
}
