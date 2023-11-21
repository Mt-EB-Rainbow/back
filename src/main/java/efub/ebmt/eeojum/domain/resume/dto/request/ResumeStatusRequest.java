package efub.ebmt.eeojum.domain.resume.dto.request;

import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResumeStatusRequest {
    ResumeStatus resumeStatus;
}
