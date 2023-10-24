package efub.ebmt.eeojum.domain.resume.controller;

import efub.ebmt.eeojum.domain.resume.dto.request.ResumeRequest;
import efub.ebmt.eeojum.domain.resume.dto.request.ResumeUpdateRequest;
import efub.ebmt.eeojum.domain.resume.service.ResumeRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeRegisterService resumeRegisterService;

    @PostMapping
    public ResponseEntity<String> resumeAdd(@RequestBody ResumeRequest resumeRequest) {
        resumeRegisterService.addResume(resumeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> resumeSave(@RequestBody ResumeUpdateRequest resumeUpdateRequest){
        resumeRegisterService.modifyResume(resumeUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
