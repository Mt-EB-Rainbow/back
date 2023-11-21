package efub.ebmt.eeojum.domain.dictionary.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String name;
    private List<JobResponse> jobResponses;

    public CategoryResponse(Long categoryId, String name, List<JobResponse> jobResponses){
        this.categoryId = categoryId;
        this.name = name;
        this.jobResponses = jobResponses;
    }
}
