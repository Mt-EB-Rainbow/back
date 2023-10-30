package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.Resume;
import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResumeResponse {
    private Long resumeId;
    private String title;
    private String introduction;
    private ResumeStatus resumeStatus;

    public ResumeResponse(Resume resume) {
        this.resumeId = resume.getResumeId();
        this.title = resume.getTitle();
        this.introduction = resume.getIntroduction();
        this.resumeStatus = resume.getResumeStatus();
    }
}
