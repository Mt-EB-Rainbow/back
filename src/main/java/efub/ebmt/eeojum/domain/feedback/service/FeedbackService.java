package efub.ebmt.eeojum.domain.feedback.service;

import efub.ebmt.eeojum.domain.feedback.dto.request.FeedbackRequest;
import efub.ebmt.eeojum.domain.feedback.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public void addFeedback(FeedbackRequest feedbackRequest){
        feedbackRepository.save(feedbackRequest.of());
    }


}
