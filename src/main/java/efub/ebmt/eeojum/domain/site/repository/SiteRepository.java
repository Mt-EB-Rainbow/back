package efub.ebmt.eeojum.domain.site.repository;

import efub.ebmt.eeojum.domain.site.domain.Site;
import efub.ebmt.eeojum.domain.site.domain.SiteType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {
    List<Site> findBySiteType(SiteType siteType);
}
