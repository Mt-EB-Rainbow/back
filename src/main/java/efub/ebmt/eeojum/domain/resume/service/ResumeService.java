package efub.ebmt.eeojum.domain.resume.service;

import efub.ebmt.eeojum.domain.resume.domain.*;
import efub.ebmt.eeojum.domain.resume.dto.request.ResumeRequest;
import efub.ebmt.eeojum.domain.resume.dto.request.ResumeUpdateRequest;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumeDetailResponse;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumeResponse;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumesResponse;
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
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final LanguageRepository languageRepository;
    private final AwardRepository awardRepository;

    public ResumeResponse addResume(ResumeRequest resumeRequest){
        Resume resume = resumeRepository.save(resumeRequest.of());
        return new ResumeResponse(resume);
    }

    public ResumeResponse modifyResume(Long resumeId, ResumeUpdateRequest resumeUpdateRequest){
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new CustomException(ErrorCode.RESUME_NOT_FOUND));
        resume.updateResume(resumeUpdateRequest.getTitle(), resumeUpdateRequest.getIntroduction());
        educationRepository.saveAll(resumeUpdateRequest.getEducations().stream()
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
                .collect(Collectors.toList()));
        return new ResumeResponse(resume);
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
                .map(ResumeResponse::new)
                .collect(Collectors.toList()));
    }

    public ResumesResponse findResumeByMember(Long memberId){
        return new ResumesResponse(resumeRepository.findByMemberId(memberId).stream()
                .map(ResumeResponse::new)
                .collect(Collectors.toList()));
    }
}
