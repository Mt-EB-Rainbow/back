package efub.ebmt.eeojum.domain.resume.dto.response;

import efub.ebmt.eeojum.domain.resume.domain.Resume;
import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResumeResponse {
    private String title;
    private String introduction;
    private ResumeStatus resumeStatus;

    @Builder
    public ResumeResponse(String title, String introduction, ResumeStatus resumeStatus){
        this.title = title;
        this.introduction = introduction;
        this.resumeStatus = resumeStatus;
    }

    public ResumeResponse(Resume resume) {
        this.title = resume.getTitle();
        this.introduction = resume.getIntroduction();
        this.resumeStatus = resume.getResumeStatus();
    }
}
