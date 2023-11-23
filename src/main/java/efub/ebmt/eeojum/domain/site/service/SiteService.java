package efub.ebmt.eeojum.domain.site.service;

import efub.ebmt.eeojum.domain.site.domain.SiteType;
import efub.ebmt.eeojum.domain.site.dto.SiteResponse;
import efub.ebmt.eeojum.domain.site.dto.SitesResponse;
import efub.ebmt.eeojum.domain.site.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SiteService {
    private final SiteRepository siteRepository;

    public SitesResponse siteList(){
        return new SitesResponse(siteRepository.findAll().stream().map(SiteResponse::new).collect(Collectors.toList()));
    }

    public SitesResponse siteListByCategory(SiteType siteType){
        return new SitesResponse(siteRepository.findBySiteType(siteType).stream().map(SiteResponse::new).collect(Collectors.toList()));
    }
}
