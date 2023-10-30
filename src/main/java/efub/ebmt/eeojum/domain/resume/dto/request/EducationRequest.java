package efub.ebmt.eeojum.domain.resume.dto.request;

import efub.ebmt.eeojum.domain.resume.domain.Education;
import efub.ebmt.eeojum.domain.resume.domain.EducationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EducationRequest {
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
