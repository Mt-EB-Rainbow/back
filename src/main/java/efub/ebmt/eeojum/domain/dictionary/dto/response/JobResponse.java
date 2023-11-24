package efub.ebmt.eeojum.domain.dictionary.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobResponse {
    private Long jobId;
    private String name;
    private String imageUrl;

    public JobResponse(Long jobId, String name, String imageUrl){
        this.jobId = jobId;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
