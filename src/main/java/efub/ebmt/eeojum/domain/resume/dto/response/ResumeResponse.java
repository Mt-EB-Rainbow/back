package efub.ebmt.eeojum.domain.resume.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import efub.ebmt.eeojum.domain.resume.domain.Resume;
import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResumeResponse {
    private Long resumeId;
    private String title;
    private String introduction;
    private ResumeStatus resumeStatus;
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
    private LocalDateTime modifiedAt;

    public ResumeResponse(Resume resume) {
        this.resumeId = resume.getResumeId();
        this.title = resume.getTitle();
        this.introduction = resume.getIntroduction();
        this.resumeStatus = resume.getResumeStatus();
        this.modifiedAt = resume.getModifiedAt();
    }
}
