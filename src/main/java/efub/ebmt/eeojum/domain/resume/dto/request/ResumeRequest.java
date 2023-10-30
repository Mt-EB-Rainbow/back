package efub.ebmt.eeojum.domain.resume.dto.request;

import efub.ebmt.eeojum.domain.resume.domain.Resume;
import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeRequest {
    @NotNull
    private Long memberId;
    @NotBlank(message = "제목은 필수로 입력되어야 합니다.")
    private String title;
    private String introduction;
    private ResumeStatus resumeStatus;

    public Resume of(){
        return Resume.builder()
                .memberId(memberId)
                .title(title)
                .introduction(introduction)
                .resumeStatus(resumeStatus)
                .build();
    }
}
