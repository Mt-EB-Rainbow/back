package efub.ebmt.eeojum.domain.example.service;

import efub.ebmt.eeojum.domain.example.dto.response.ExampleResponse;
import efub.ebmt.eeojum.domain.example.dto.response.ExamplesResponse;
import efub.ebmt.eeojum.domain.example.repository.ExampleRepository;
import efub.ebmt.eeojum.global.exception.CustomException;
import efub.ebmt.eeojum.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExampleService {
    private final ExampleRepository exampleRepository;

    public ExamplesResponse exampleList(){
        return new ExamplesResponse(exampleRepository.findAll());
    }

    public ExampleResponse exampleDetail(Long exampleId){
        return new ExampleResponse(exampleRepository.findByExampleId(exampleId).orElseThrow(() -> new CustomException(ErrorCode.EXAMPLE_NOT_FOUND)));
    }
}
