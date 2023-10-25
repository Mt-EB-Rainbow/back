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

    public void addResume(ResumeRequest resumeRequest){
        Resume resume = resumeRepository.save(resumeRequest.of());
    }

    public void modifyResume(ResumeUpdateRequest resumeUpdateRequest){
        educationRepository.saveAll(resumeUpdateRequest.getEducations());
        experienceRepository.saveAll(resumeUpdateRequest.getExperiences());
        languageRepository.saveAll(resumeUpdateRequest.getLanguages());
        awardRepository.saveAll(resumeUpdateRequest.getAwards());
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
}
