package efub.ebmt.eeojum.domain.feedback.service;

import efub.ebmt.eeojum.domain.feedback.domain.Feedback;
import efub.ebmt.eeojum.domain.feedback.dto.request.FeedbackRequest;
import efub.ebmt.eeojum.domain.feedback.dto.request.FeedbackUpdateRequest;
import efub.ebmt.eeojum.domain.feedback.dto.response.FeedbackResponse;
import efub.ebmt.eeojum.domain.feedback.repository.FeedbackRepository;
import efub.ebmt.eeojum.domain.resume.domain.Resume;
import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import efub.ebmt.eeojum.domain.resume.repository.ResumeRepository;
import efub.ebmt.eeojum.global.exception.CustomException;
import efub.ebmt.eeojum.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ResumeRepository resumeRepository;

    public FeedbackResponse addFeedback(FeedbackRequest feedbackRequest){
        Resume resume = resumeRepository.findById(feedbackRequest.getResumeId()).orElseThrow(() -> new CustomException(ErrorCode.RESUME_NOT_FOUND));
        Feedback feedback = feedbackRepository.save(feedbackRequest.of());
        resume.updateResumeStatus(ResumeStatus.ARRIVED);
        return new FeedbackResponse(feedback);
    }

    public FeedbackResponse modifyFeedback(Long feedbackId, FeedbackUpdateRequest feedbackUpdateRequest){
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new CustomException(ErrorCode.FEEDBACK_NOT_FOUND));
        feedback.updateFeedback(feedbackUpdateRequest);
        return new FeedbackResponse(feedback);
    }
}
