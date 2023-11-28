package efub.ebmt.eeojum.domain.site.dto;

import efub.ebmt.eeojum.domain.site.domain.Site;
import efub.ebmt.eeojum.domain.site.domain.SiteType;
import efub.ebmt.eeojum.domain.site.repository.SiteRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SiteResponse {
    private Long siteId;
    private String name; //사이트 이름
    private SiteType siteType; //사이트 종류
    private String siteUrl; //사이트 url
    private String description; //사이트 설명
    private String imageUrl; //사이트 이미지 url

    public SiteResponse(Site site){
        this.siteId = site.getSiteId();
        this.name = site.getName();
        this.siteType = site.getSiteType();
        this.siteUrl = site.getSiteUrl();
        this.description = site.getDescription();
        this.imageUrl = site.getImageUrl();
    }
}
