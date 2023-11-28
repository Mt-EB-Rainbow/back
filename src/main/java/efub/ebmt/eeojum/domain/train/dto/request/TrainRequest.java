package efub.ebmt.eeojum.domain.train.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TrainRequest {
    private String query; //검색어
    private String category; //카테고리
    private String classDomain; //교육 분야
}
