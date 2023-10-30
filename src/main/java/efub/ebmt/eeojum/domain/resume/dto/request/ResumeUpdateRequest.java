package efub.ebmt.eeojum.domain.resume.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeUpdateRequest {
    private String title;
    private String introduction;
    private List<EducationRequest> educations;
    private List<ExperienceRequest> experiences;
    private List<LanguageRequest> languages;
    private List<AwardRequest> awards;
}
