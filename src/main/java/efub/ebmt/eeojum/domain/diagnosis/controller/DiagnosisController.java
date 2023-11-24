package efub.ebmt.eeojum.domain.diagnosis.controller;

import efub.ebmt.eeojum.domain.diagnosis.dto.request.DiagnosisRequest;
import efub.ebmt.eeojum.domain.diagnosis.dto.response.DiagnosisResponse;
import efub.ebmt.eeojum.domain.diagnosis.dto.response.QuestionsResponse;
import efub.ebmt.eeojum.domain.diagnosis.service.DiagnosisService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @GetMapping("/questions")
    @Operation(summary = "질문, 선택지 리스트 조회")
    public ResponseEntity<QuestionsResponse> getQuestionList(){
        QuestionsResponse questionsResponse = diagnosisService.questionList();
        return new ResponseEntity<>(questionsResponse, HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(summary = "상황 기반 직무 추천 선택지 보내면 결과 직업 리스트(id, name, imageUrl) 반환")
    public ResponseEntity<DiagnosisResponse> postAnswerToDiagnosis(@PathVariable DiagnosisRequest diagnosisRequest){
        DiagnosisResponse diagnosisResponse = diagnosisService.diagnosisResult(diagnosisRequest);
        return new ResponseEntity<>(diagnosisResponse, HttpStatus.OK);
    }
}
