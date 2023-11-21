package efub.ebmt.eeojum.domain.site.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SitesResponse {
    List<SiteResponse> siteResponseList;

    public SitesResponse(List<SiteResponse> siteResponseList) {
        this.siteResponseList = siteResponseList;
    }
}
