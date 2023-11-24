package efub.ebmt.eeojum.domain.diagnosis.dto.response;

import efub.ebmt.eeojum.domain.dictionary.dto.response.JobResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DiagnosisResponse {
    List<JobResponse> jobsResponse;

    public DiagnosisResponse(List<JobResponse> jobsResponse){
        this.jobsResponse = jobsResponse;
    }
}
