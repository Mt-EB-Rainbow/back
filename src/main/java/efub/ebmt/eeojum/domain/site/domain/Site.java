package efub.ebmt.eeojum.domain.site.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long siteId;

    private String name; //사이트 이름

    private SiteType category; //사이트 종류

    private String siteUrl; //사이트 url

    private String description; //사이트 설명

    private String imageUrl; //사이트 이미지 url
}
