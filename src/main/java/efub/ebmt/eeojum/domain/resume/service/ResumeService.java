package efub.ebmt.eeojum.domain.resume.service;

import efub.ebmt.eeojum.domain.feedback.repository.FeedbackRepository;
import efub.ebmt.eeojum.domain.resume.domain.*;
import efub.ebmt.eeojum.domain.resume.dto.request.*;
import efub.ebmt.eeojum.domain.resume.dto.response.*;
import efub.ebmt.eeojum.domain.resume.repository.*;
import efub.ebmt.eeojum.global.exception.CustomException;
import efub.ebmt.eeojum.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final FeedbackRepository feedbackRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final LanguageRepository languageRepository;
    private final AwardRepository awardRepository;

    public ResumeResponse addResume(ResumeRequest resumeRequest){
        Resume resume = resumeRepository.save(resumeRequest.of());
        return new ResumeResponse(resume, feedbackCount(resume.getResumeId()));
    }

    public ResumeResponse modifyResume(Long resumeId, ResumeUpdateRequest resumeUpdateRequest){
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new CustomException(ErrorCode.RESUME_NOT_FOUND));
        resume.updateResume(resumeUpdateRequest);
        resumeRepository.save(resume);
        /*educationRepository.saveAll(resumeUpdateRequest.getEducations().stream()
                .map(r -> r.of(resumeId))
                .collect(Collectors.toList()));
        experienceRepository.saveAll(resumeUpdateRequest.getExperiences().stream()
                .map(r -> r.of(resumeId))
                .collect(Collectors.toList()));
        languageRepository.saveAll(resumeUpdateRequest.getLanguages().stream()
                .map(r -> r.of(resumeId))
                .collect(Collectors.toList()));
        awardRepository.saveAll(resumeUpdateRequest.getAwards().stream()
                .map(r -> r.of(resumeId))
                .collect(Collectors.toList()));*/
        return new ResumeResponse(resume, feedbackCount(resume.getResumeId()));
    }

    public void deleteResume(Long resumeId){
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new CustomException(ErrorCode.RESUME_NOT_FOUND));
        resumeRepository.delete(resume);
    }

    public ResumeResponse modifyResumeStatus(Long resumeId, ResumeStatusRequest resumeStatusRequest){
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new CustomException(ErrorCode.RESUME_NOT_FOUND));
        resume.updateResumeStatus(resumeStatusRequest.getResumeStatus());
        return new ResumeResponse(resume, feedbackCount(resume.getResumeId()));
    }

    public Long feedbackCount(Long resumeId){
        return (long) feedbackRepository.findByResumeId(resumeId).size();
    }

    public ResumeDetailResponse findResume(Long resumeId){
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new CustomException(ErrorCode.RESUME_NOT_FOUND));
        List<Education> educations = educationRepository.findByResumeId(resumeId);
        List<Experience> experiences = experienceRepository.findByResumeId(resumeId);
        List<Language> languages = languageRepository.findByResumeId(resumeId);
        List<Award> awards = awardRepository.findByResumeId(resumeId);
        return new ResumeDetailResponse(resume, educations, experiences, languages, awards);
    }

    public ResumesResponse findAllResume(){
        return new ResumesResponse(resumeRepository.findAll().stream()
                .map(r -> new ResumeResponse(r, feedbackCount(r.getResumeId())))
                .collect(Collectors.toList()));
    }

    public ResumesResponse findResumeByMember(Long memberId){
        return new ResumesResponse(resumeRepository.findByMemberId(memberId).stream()
                .map(r -> new ResumeResponse(r, feedbackCount(r.getResumeId())))
                .collect(Collectors.toList()));
    }

    public ResumesResponse findResumeByStatus(ResumeStatus resumeStatus){
        return new ResumesResponse(resumeRepository.findByResumeStatus(resumeStatus).stream()
                .map(r -> new ResumeResponse(r, feedbackCount(r.getResumeId())))
                .collect(Collectors.toList()));
    }

    public AwardResponse saveAward(Long resumeId, AwardRequest awardRequest){
        return new AwardResponse(awardRepository.save(awardRequest.of(resumeId)));
    }

    public AwardResponse updateAward(Long awardId, AwardRequest awardRequest){
        return new AwardResponse(awardRepository.save(awardRepository.findById(awardId)
                .orElseThrow(() -> new CustomException(ErrorCode.AWARD_NOT_FOUND))
                .update(awardRequest)));
    }

    public void deleteAward(Long awardId){
        awardRepository.delete(awardRepository.findById(awardId)
                .orElseThrow(() -> new CustomException(ErrorCode.AWARD_NOT_FOUND)));
    }

    public EducationResponse saveEducation(Long educationId, EducationRequest educationRequest){
        return new EducationResponse(educationRepository.save(educationRequest.of(educationId)));
    }

    public EducationResponse updateEducation(Long educationId, EducationRequest educationRequest){
        return new EducationResponse(educationRepository.save(educationRepository.findById(educationId)
                .orElseThrow(() -> new CustomException(ErrorCode.EDUCATION_NOT_FOUND))
                .update(educationRequest)));
    }

    public void deleteEducation(Long educationId){
        educationRepository.delete(educationRepository.findById(educationId)
                .orElseThrow(() -> new CustomException(ErrorCode.EDUCATION_NOT_FOUND)));
    }

    public ExperienceResponse saveExperience(Long resumeId, ExperienceRequest experienceRequest){
        return new ExperienceResponse(experienceRepository.save(experienceRequest.of(resumeId)));
    }

    public ExperienceResponse updateExperience(Long experienceId, ExperienceRequest experienceRequest){
        return new ExperienceResponse(experienceRepository.save(experienceRepository.findById(experienceId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXPERIENCE_NOT_FOUND))
                .update(experienceRequest)));
    }

    public void deleteExperience(Long experienceId){
        experienceRepository.delete(experienceRepository.findById(experienceId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXPERIENCE_NOT_FOUND)));
    }

    public LanguageResponse saveLanguage(Long resumeId, LanguageRequest languageRequest){
        return new LanguageResponse(languageRepository.save(languageRequest.of(resumeId)));
    }

    public LanguageResponse updateLanguage(Long languageId, LanguageRequest languageRequest){
        return new LanguageResponse(languageRepository.save(languageRepository.findById(languageId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXPERIENCE_NOT_FOUND))
                .update(languageRequest)));
    }

    public void deleteLanguage(Long languageId){
        languageRepository.delete(languageRepository.findById(languageId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXPERIENCE_NOT_FOUND)));
    }
}