package efub.ebmt.eeojum.domain.resume.controller;

import efub.ebmt.eeojum.domain.resume.dto.request.ResumeRequest;
import efub.ebmt.eeojum.domain.resume.dto.request.ResumeUpdateRequest;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumeDetailResponse;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumesResponse;
import efub.ebmt.eeojum.domain.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<String> resumeAdd(@RequestBody ResumeRequest resumeRequest) {
        resumeService.addResume(resumeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{resumeId}")
    public ResponseEntity<String> resumeSave(@PathVariable Long resumeId, @RequestBody ResumeUpdateRequest resumeUpdateRequest){
        resumeService.modifyResume(resumeId, resumeUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResumesResponse> resumeList(){
        ResumesResponse resumesResponse = resumeService.findAllResume();
        return new ResponseEntity<ResumesResponse>(HttpStatus.OK);
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeDetailResponse> resumeDetails(@PathVariable Long resumeId){
        ResumeDetailResponse resumeDetailResponse = resumeService.findResume(resumeId);
        return new ResponseEntity<ResumeDetailResponse>(HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ResumesResponse> resumeListByMember(@PathVariable Long memberId){
        ResumesResponse resumesResponse = resumeService.findResumeByMember(memberId);
        return new ResponseEntity<ResumesResponse>(HttpStatus.OK);
    }
}
