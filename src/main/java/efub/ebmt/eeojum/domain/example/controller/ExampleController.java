package efub.ebmt.eeojum.domain.example.controller;

import efub.ebmt.eeojum.domain.example.dto.response.ExamplesResponse;
import efub.ebmt.eeojum.domain.example.service.ExampleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examples")
@RequiredArgsConstructor
public class ExampleController {
    private final ExampleService exampleService;

    @GetMapping
    @Operation(summary = "성공 사례 리스트 조회")
    public ResponseEntity<ExamplesResponse> getExamples(){
        return new ResponseEntity<>(exampleService.exampleList(), HttpStatus.OK);
    }
}
