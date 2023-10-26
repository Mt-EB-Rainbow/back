package efub.ebmt.eeojum.domain.resume.dto.request;

import efub.ebmt.eeojum.domain.resume.domain.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeUpdateRequest {
    private Long resumeId;
    private String title;
    private String introduction;
    private ResumeStatus resumeStatus;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Language> languages;
    private List<Award> awards;
}
