package efub.ebmt.eeojum.domain.resume.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumesResponse {
    List<ResumeResponse> resumeResponses;
}
