package efub.ebmt.eeojum.domain.resume.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import efub.ebmt.eeojum.domain.resume.domain.Resume;
import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ResumeResponse {
    private Long resumeId;
    private String title;
    private String jobName;
    private String introduction;
    private ResumeStatus resumeStatus;
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
    private LocalDateTime modifiedAt;
    private Boolean isPrivate;
    private Long feedbackCnt;

    public ResumeResponse(Resume resume, Long feedbackCnt) {
        this.resumeId = resume.getResumeId();
        this.title = resume.getTitle();
        this.jobName = resume.getJobName();
        this.introduction = resume.getIntroduction();
        this.resumeStatus = resume.getResumeStatus();
        this.modifiedAt = resume.getModifiedAt();
        this.isPrivate = resume.getIsPrivate();
        this.feedbackCnt = feedbackCnt;
    }
}
