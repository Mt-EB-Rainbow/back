package efub.ebmt.eeojum.domain.feedback.service;

import efub.ebmt.eeojum.domain.feedback.domain.Feedback;
import efub.ebmt.eeojum.domain.feedback.dto.request.FeedbackRequest;
import efub.ebmt.eeojum.domain.feedback.dto.response.FeedbackResponse;
import efub.ebmt.eeojum.domain.feedback.dto.response.FeedbacksResponse;
import efub.ebmt.eeojum.domain.feedback.repository.FeedbackRepository;
import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    public FeedbackResponse addFeedback(FeedbackRequest feedbackRequest){
        Resume resume = resumeRepository.findById(feedbackRequest.getResumeId())
                .orElseThrow(() -> new CustomException(ErrorCode.RESUME_NOT_FOUND));
        Member member = memberRepository.findById(feedbackRequest.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Feedback feedback = feedbackRepository.save(feedbackRequest.of());
        resume.updateResumeStatus(ResumeStatus.ARRIVED);
        return new FeedbackResponse(feedback, member.getName());
    }

    public FeedbacksResponse feedbackList(Long resumeId){
        return new FeedbacksResponse(feedbackRepository.findByResumeId(resumeId).stream()
                .map(f -> new FeedbackResponse(f, memberRepository.findById(f.getMemberId())
                        .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)).getName()))
                .collect(Collectors.toList()));
    }
}
