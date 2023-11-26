package efub.ebmt.eeojum.domain.education.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class EducationsResponse {
    List<EducationResponse> educations;

    public EducationsResponse(List<EducationResponse> educations){
        this.educations = educations;
    }
}
