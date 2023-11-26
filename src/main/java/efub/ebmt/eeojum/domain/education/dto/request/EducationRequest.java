package efub.ebmt.eeojum.domain.education.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EducationRequest {
    private String query; //검색어
    private String type;  //온라인, 오프라인
    private String category; //분류
}
