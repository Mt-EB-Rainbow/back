package efub.ebmt.eeojum.domain.feedback.controller;

import efub.ebmt.eeojum.domain.feedback.dto.request.FeedbackRequest;
import efub.ebmt.eeojum.domain.feedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<String> feedbackAdd(@RequestBody FeedbackRequest feedbackRequest) {
        feedbackService.addFeedback(feedbackRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
