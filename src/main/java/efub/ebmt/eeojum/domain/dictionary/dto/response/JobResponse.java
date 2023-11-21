package efub.ebmt.eeojum.domain.dictionary.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobResponse {
    private Long jobId;
    private String name;

    public JobResponse(Long jobId, String name){
        this.jobId = jobId;
        this.name = name;
    }
}
