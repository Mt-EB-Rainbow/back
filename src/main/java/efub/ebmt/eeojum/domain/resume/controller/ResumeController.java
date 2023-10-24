package efub.ebmt.eeojum.domain.resume.controller;

import efub.ebmt.eeojum.domain.resume.dto.request.ResumeRequest;
import efub.ebmt.eeojum.domain.resume.dto.request.ResumeUpdateRequest;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumeResponse;
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

    @PutMapping
    public ResponseEntity<String> resumeSave(@RequestBody ResumeUpdateRequest resumeUpdateRequest){
        resumeService.modifyResume(resumeUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponse> resumeDetails(@PathVariable Long resumeId){
        ResumeResponse resumeResponse = resumeService.findResume(resumeId);
        return new ResponseEntity<ResumeResponse>(HttpStatus.OK);
    }
}
