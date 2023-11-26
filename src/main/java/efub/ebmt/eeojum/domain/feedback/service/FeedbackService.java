package efub.ebmt.eeojum.domain.feedback.service;

import efub.ebmt.eeojum.domain.feedback.dto.request.FeedbackRequest;
import efub.ebmt.eeojum.domain.feedback.dto.response.FeedbackResponse;
import efub.ebmt.eeojum.domain.feedback.dto.response.FeedbacksResponse;
import efub.ebmt.eeojum.domain.feedback.repository.FeedbackRepository;
import efub.ebmt.eeojum.domain.resume.domain.Resume;
import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import efub.ebmt.eeojum.domain.resume.repository.ResumeRepository;
import efub.ebmt.eeojum.global.exception.CustomException;
import efub.ebmt.eeojum.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ResumeRepository resumeRepository;

    public void addFeedback(FeedbackRequest feedbackRequest){
        feedbackRepository.save(feedbackRequest.of());
        Resume resume = resumeRepository.findById(feedbackRequest.getResumeId()).orElseThrow(() -> new CustomException(ErrorCode.RESUME_NOT_FOUND));
        resume.updateResumeStatus(ResumeStatus.ARRIVED);
    }

    public FeedbacksResponse feedbackList(Long resumeId){
        return new FeedbacksResponse(feedbackRepository.findByResumeId(resumeId).stream()
                .map(FeedbackResponse::new)
                .collect(Collectors.toList()));
    }
}
