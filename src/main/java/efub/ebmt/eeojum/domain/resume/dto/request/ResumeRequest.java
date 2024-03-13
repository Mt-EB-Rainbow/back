package efub.ebmt.eeojum.domain.resume.dto.request;

import efub.ebmt.eeojum.domain.resume.domain.Resume;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ResumeRequest {
    @NotNull
    private Long memberId;

    public Resume of(){
        return Resume.builder()
                .memberId(memberId)
                .build();
    }
}
