package efub.ebmt.eeojum.domain.resume.service;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.domain.member.repository.MemberRepository;
import efub.ebmt.eeojum.domain.resume.domain.Resume;
import efub.ebmt.eeojum.domain.resume.dto.request.ResumeRequest;
import efub.ebmt.eeojum.domain.resume.dto.request.ResumeUpdateRequest;
import efub.ebmt.eeojum.domain.resume.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeRegisterService {
    private final MemberRepository memberRepository;
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
}
