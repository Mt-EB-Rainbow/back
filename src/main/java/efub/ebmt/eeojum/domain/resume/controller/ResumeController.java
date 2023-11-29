package efub.ebmt.eeojum.domain.resume.controller;

import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import efub.ebmt.eeojum.domain.resume.dto.request.*;
import efub.ebmt.eeojum.domain.resume.dto.response.*;
import efub.ebmt.eeojum.domain.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
@Tag(name = "이력서 관련 API", description = "이력서 생성, 작성, 조회, 피드백 요청 기능을 제공합니다.")
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    @Operation(summary = "이력서 생성 API입니다. 멤버 아이디를 입력해 이력서를 생성합니다.")
    public ResponseEntity<ResumeResponse> resumeAdd(@RequestBody ResumeRequest resumeRequest) {
        ResumeResponse resumeResponse = resumeService.addResume(resumeRequest);
        return new ResponseEntity<>(resumeResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{resumeId}")
    @Operation(summary = "이력서 제목, 자기소개, 직업, 공개 여부 수정 API입니다.")
    public ResponseEntity<ResumeResponse> resumeSave(@PathVariable Long resumeId, @RequestBody ResumeUpdateRequest resumeUpdateRequest){
        ResumeResponse resumeResponse = resumeService.modifyResume(resumeId, resumeUpdateRequest);
        return new ResponseEntity<>(resumeResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{resumeId}")
    @Operation(summary = "이력서 삭제")
    public ResponseEntity<String> resumeDelete(@PathVariable Long resumeId){
        resumeService.deleteResume(resumeId);
        return new ResponseEntity<>("이력서 id" + resumeId + "삭제", HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "이력서 전체 조회 API입니다.")
    public ResponseEntity<ResumesResponse> resumeList(){
        ResumesResponse resumesResponse = resumeService.findAllResume();
        return new ResponseEntity<>(resumesResponse, HttpStatus.OK);
    }

    @GetMapping("/{resumeId}")
    @Operation(summary = "이력서 상세 조회 API입니다.")
    public ResponseEntity<ResumeDetailResponse> resumeDetails(@PathVariable Long resumeId){
        ResumeDetailResponse resumeDetailResponse = resumeService.findResume(resumeId);
        return new ResponseEntity<>(resumeDetailResponse, HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}")
    @Operation(summary = "이력서 멤버 별 조회 API입니다.")
    public ResponseEntity<ResumesResponse> resumeListByMember(@PathVariable Long memberId){
        ResumesResponse resumesResponse = resumeService.findResumeByMember(memberId);
        return new ResponseEntity<>(resumesResponse, HttpStatus.OK);
    }

    @GetMapping("/waiting")
    @Operation(summary = "피드백 대기 중인 이력서 조회 API입니다. ")
    public ResponseEntity<ResumesResponse> feedbackWaitingList(){
        ResumesResponse resumesResponse = resumeService.findResumeByStatus(ResumeStatus.WAITING);
        return new ResponseEntity<>(resumesResponse, HttpStatus.OK);
    }

    @PostMapping("/{resumeId}/award")
    public ResponseEntity<AwardResponse> awardSave(@PathVariable Long resumeId, @RequestBody AwardRequest awardRequest){
        AwardResponse awardResponse = resumeService.saveAward(resumeId, awardRequest);
        return new ResponseEntity<>(awardResponse, HttpStatus.OK);
    }

    @PutMapping("/award/{awardId}")
    public ResponseEntity<AwardResponse> awardUpdate(@PathVariable Long awardId, @RequestBody AwardRequest awardRequest){
        AwardResponse awardResponse = resumeService.updateAward(awardId, awardRequest);
        return new ResponseEntity<>(awardResponse, HttpStatus.OK);
    }

    @DeleteMapping("/award/{awardId}")
    public ResponseEntity<String> awardDelete(@PathVariable Long awardId){
        resumeService.deleteAward(awardId);
        return new ResponseEntity<>("수상 삭제", HttpStatus.OK);
    }

    @PostMapping("/{resumeId}/education")
    public ResponseEntity<EducationResponse> educationSave(@PathVariable Long resumeId, @RequestBody EducationRequest educationRequest){
        EducationResponse educationResponse = resumeService.saveEducation(resumeId, educationRequest);
        return new ResponseEntity<>(educationResponse, HttpStatus.OK);
    }

    @PutMapping("/education/{educationId}")
    public ResponseEntity<EducationResponse> educationUpdate(@PathVariable Long educationId, @RequestBody EducationRequest educationRequest){
        EducationResponse educationResponse = resumeService.updateEducation(educationId, educationRequest);
        return new ResponseEntity<>(educationResponse, HttpStatus.OK);
    }

    @DeleteMapping("/education/{educationId}")
    public ResponseEntity<String> educationDelete(@PathVariable Long educationId){
        resumeService.deleteEducation(educationId);
        return new ResponseEntity<>("학력 삭제", HttpStatus.OK);
    }

    @PostMapping("/{resumeId}/experience")
    public ResponseEntity<ExperienceResponse> experienceSave(@PathVariable Long resumeId, @RequestBody ExperienceRequest experienceRequest){
        ExperienceResponse experienceResponse = resumeService.saveExperience(resumeId, experienceRequest);
        return new ResponseEntity<>(experienceResponse, HttpStatus.OK);
    }

    @PutMapping("/experience/{experienceId}")
    public ResponseEntity<ExperienceResponse> experienceUpdate(@PathVariable Long experienceId, @RequestBody ExperienceRequest experienceRequest){
        ExperienceResponse experienceResponse = resumeService.updateExperience(experienceId, experienceRequest);
        return new ResponseEntity<>(experienceResponse, HttpStatus.OK);
    }

    @DeleteMapping("/experience/{experienceId}")
    public ResponseEntity<String> experienceDelete(@PathVariable Long experienceId){
        resumeService.deleteExperience(experienceId);
        return new ResponseEntity<>("학력 삭제", HttpStatus.OK);
    }

    @PostMapping("/{resumeId}/language")
    public ResponseEntity<LanguageResponse> languageSave(@PathVariable Long resumeId, @RequestBody LanguageRequest languageRequest){
        LanguageResponse languageResponse = resumeService.saveLanguage(resumeId, languageRequest);
        return new ResponseEntity<>(languageResponse, HttpStatus.OK);
    }

    @PutMapping("/language/{languageId}")
    public ResponseEntity<LanguageResponse> languageUpdate(@PathVariable Long languageId, @RequestBody LanguageRequest languageRequest){
        LanguageResponse languageResponse = resumeService.updateLanguage(languageId, languageRequest);
        return new ResponseEntity<>(languageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/language/{languageId}")
    public ResponseEntity<String> languageDelete(@PathVariable Long languageId){
        resumeService.deleteLanguage(languageId);
        return new ResponseEntity<>("학력 삭제", HttpStatus.OK);
    }
}
