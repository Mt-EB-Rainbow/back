package efub.ebmt.eeojum.domain.feedback.controller;

import efub.ebmt.eeojum.domain.feedback.dto.request.FeedbackRequest;
import efub.ebmt.eeojum.domain.feedback.dto.request.FeedbackUpdateRequest;
import efub.ebmt.eeojum.domain.feedback.dto.response.FeedbackResponse;
import efub.ebmt.eeojum.domain.feedback.dto.response.FeedbacksResponse;
import efub.ebmt.eeojum.domain.feedback.service.FeedbackService;
import efub.ebmt.eeojum.domain.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
@Tag(name = "피드백 관련 API", description = "피드백 생성, 조회 기능을 제공합니다.")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final ResumeService resumeService;

    @PostMapping
    @Operation(summary = "피드백 생성 API입니다. 피드백 생성 시 이력서의 상태(resumeStatus)가 ARRIVED로 변경됩니다. ")
    public ResponseEntity<FeedbackResponse> feedbackAdd(@RequestBody FeedbackRequest feedbackRequest) {
        FeedbackResponse feedbackResponse = feedbackService.addFeedback(feedbackRequest);
        return new ResponseEntity<>(feedbackResponse, HttpStatus.CREATED);
    }


    @GetMapping("/{resumeId}")
    @Operation(summary = "피드백 조회")
    public ResponseEntity<FeedbacksResponse> getFeedback(@PathVariable Long resumeId){
        return new ResponseEntity<>(feedbackService.feedbackList(resumeId), HttpStatus.OK);
    }
}
