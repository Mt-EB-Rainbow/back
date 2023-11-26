package efub.ebmt.eeojum.domain.dictionary.controller;

import efub.ebmt.eeojum.domain.dictionary.dto.response.CategoriesResponse;
import efub.ebmt.eeojum.domain.dictionary.dto.response.JobDetailResponse;
import efub.ebmt.eeojum.domain.dictionary.dto.response.JobDetailResponses;
import efub.ebmt.eeojum.domain.dictionary.dto.response.JobsResponse;
import efub.ebmt.eeojum.domain.dictionary.service.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
@Tag(name = "직무 백과 정보 API", description = "직무 백과 정보 조회 API를 제공합니다.")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @GetMapping("/categories")
    @Operation(summary = "카테고리 리스트와 해당 카테고리에 속하는 직업 리스트 조회")
    public ResponseEntity<CategoriesResponse> getJobCategoryList(){
        CategoriesResponse categoriesResponse = dictionaryService.categoryList();
        return new ResponseEntity<>(categoriesResponse, HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    @Operation(summary = "직업 상세 정보")
    public ResponseEntity<JobDetailResponse> getJobDetails(@PathVariable Long jobId){
        return new ResponseEntity<>(dictionaryService.jobDetails(jobId), HttpStatus.OK);
    }

    @GetMapping("/search/{query}")
    @Operation(summary = "직무 백과 검색")
    public ResponseEntity<JobDetailResponses> getJobSearch(@PathVariable String query){
        return new ResponseEntity<>(dictionaryService.searchJobs(query), HttpStatus.OK);
    }

}
