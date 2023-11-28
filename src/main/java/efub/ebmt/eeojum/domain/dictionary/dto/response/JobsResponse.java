package efub.ebmt.eeojum.domain.dictionary.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class JobsResponse {
    private List<JobResponse> jobsResponse;

    public JobsResponse(List<JobResponse> jobsResponse){
        this.jobsResponse = jobsResponse;
    }
}
