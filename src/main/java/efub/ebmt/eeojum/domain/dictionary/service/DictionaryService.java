package efub.ebmt.eeojum.domain.dictionary.service;

import efub.ebmt.eeojum.domain.dictionary.domain.Category;
import efub.ebmt.eeojum.domain.dictionary.domain.Job;
import efub.ebmt.eeojum.domain.dictionary.dto.response.CategoriesResponse;
import efub.ebmt.eeojum.domain.dictionary.dto.response.CategoryResponse;
import efub.ebmt.eeojum.domain.dictionary.dto.response.JobDetailResponse;
import efub.ebmt.eeojum.domain.dictionary.dto.response.JobResponse;
import efub.ebmt.eeojum.domain.dictionary.repository.CategoryRepository;
import efub.ebmt.eeojum.domain.dictionary.repository.JobRepository;
import efub.ebmt.eeojum.global.exception.CustomException;
import efub.ebmt.eeojum.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DictionaryService {
    private final CategoryRepository categoryRepository;
    private final JobRepository jobRepository;

    public List<JobResponse> jobList(Long categoryId){
        return jobRepository.findByCategoryId(categoryId)
                .stream()
                .map(res -> new JobResponse(res.getJobId(), res.getName()))
                .collect(Collectors.toList());
    }

    public CategoriesResponse categoryList(){
        List<CategoryResponse> categoryList = categoryRepository.findAll()
                .stream()
                .map(res -> new CategoryResponse(res.getCategoryId(), res.getName(), jobList(res.getCategoryId())))
                .collect(Collectors.toList());
        return new CategoriesResponse(categoryList);
    }

    public JobDetailResponse jobDetails(Long jobId){
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));
        return new JobDetailResponse(job);
    }
}
