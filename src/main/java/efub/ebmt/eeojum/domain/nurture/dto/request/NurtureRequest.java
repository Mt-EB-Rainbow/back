package efub.ebmt.eeojum.domain.nurture.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NurtureRequest {
    private String district="전체"; //자치구
    private String dong=""; //행정동
    private String pageNum="1"; //페이지네이션 번호
}
