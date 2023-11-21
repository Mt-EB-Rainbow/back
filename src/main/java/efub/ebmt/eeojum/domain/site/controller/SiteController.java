package efub.ebmt.eeojum.domain.site.controller;

import efub.ebmt.eeojum.domain.site.dto.SitesResponse;
import efub.ebmt.eeojum.domain.site.service.SiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sites")
@RequiredArgsConstructor
@Tag(name = "도움되는 사이트 관련 API", description = "도움되는 사이트 조회 기능을 제공합니다.")
public class SiteController {
    private final SiteService siteService;

    @GetMapping
    @Operation(summary = "도움되는 사이트 정보 조회 API")
    public ResponseEntity<SitesResponse> getSiteList(){
        return new ResponseEntity<>(siteService.siteList(), HttpStatus.OK);
    }
}
