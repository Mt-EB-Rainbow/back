package efub.ebmt.eeojum.domain.dictionary.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class JobDetailResponses {
    List<JobDetailResponse> jobDetailList;

    public JobDetailResponses(List<JobDetailResponse> jobDetailList){
        this.jobDetailList = jobDetailList;
    }
}
