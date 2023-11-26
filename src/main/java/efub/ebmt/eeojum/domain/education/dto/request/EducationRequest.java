package efub.ebmt.eeojum.domain.education.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EducationRequest {
    private String query; //검색어
    private String category; //카테고리
    private String classDomain; //교육 분야
}
