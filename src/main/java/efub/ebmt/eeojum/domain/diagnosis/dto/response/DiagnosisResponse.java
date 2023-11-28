package efub.ebmt.eeojum.domain.diagnosis.dto.response;

import efub.ebmt.eeojum.domain.dictionary.dto.response.JobResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DiagnosisResponse {
    private List<JobResponse> jobsResponse;
    private Boolean needNurture;

    public DiagnosisResponse(Boolean needNurture, List<JobResponse> jobsResponse){
        this.jobsResponse = jobsResponse;
        this.needNurture = needNurture;
    }
}
