package efub.ebmt.eeojum.domain.nurture.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NurturesResponse {
    private Long pageCnt;
    private List<NurtureResponse> nurturesResponse;

    public NurturesResponse(Long pageCnt, List<NurtureResponse> nurturesResponse){
        this.pageCnt = pageCnt;
        this.nurturesResponse = nurturesResponse;
    }
}
