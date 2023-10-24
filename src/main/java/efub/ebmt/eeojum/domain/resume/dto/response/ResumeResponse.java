package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResumeResponse {
    private String title;
    private String introduction;
    private ResumeStatus resumeStatus;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Language> languages;
    private List<Award> awards;

    @Builder
    public ResumeResponse (Resume resume, List<Education> educations, List<Experience> experiences, List<Language> languages, List<Award> awards){
        this.title = resume.getTitle();
        this.introduction = resume.getIntroduction();
        this.resumeStatus = resume.getResumeStatus();
        this.educations = educations;
        this.experiences = experiences;
        this.languages = languages;
        this.awards = awards;
    }
}
