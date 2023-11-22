package efub.ebmt.eeojum.domain.nurture.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class NurtureResponse {
    private String rowId;
    private String schoolName;
    private String type;
    private String capacity;
    private String current;
    private String contact;
    private String address;
}
