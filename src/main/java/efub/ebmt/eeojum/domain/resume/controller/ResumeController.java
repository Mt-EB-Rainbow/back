package efub.ebmt.eeojum.domain.resume.controller;

import efub.ebmt.eeojum.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    @PostMapping
    public ResponseEntity<String> createResume(Member member) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
