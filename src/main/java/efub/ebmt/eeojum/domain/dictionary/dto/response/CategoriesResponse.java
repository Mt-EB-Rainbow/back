package efub.ebmt.eeojum.domain.dictionary.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CategoriesResponse {
    private List<CategoryResponse> categoryResponses;

    public CategoriesResponse(List<CategoryResponse> categoryResponses){
        this.categoryResponses = categoryResponses;
    }
}
