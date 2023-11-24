package efub.ebmt.eeojum.domain.diagnosis.controller;

import efub.ebmt.eeojum.domain.diagnosis.dto.response.QuestionResponse;
import efub.ebmt.eeojum.domain.diagnosis.dto.response.QuestionsResponse;
import efub.ebmt.eeojum.domain.diagnosis.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @GetMapping("/questions")
    public ResponseEntity<QuestionsResponse> getQuestionList(){
        QuestionsResponse questionsResponse = diagnosisService.questionList();
        return new ResponseEntity<>(questionsResponse, HttpStatus.OK);
    }
}
