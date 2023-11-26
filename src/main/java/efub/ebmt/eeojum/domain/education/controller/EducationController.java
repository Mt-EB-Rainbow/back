package efub.ebmt.eeojum.domain.education.controller;

import efub.ebmt.eeojum.domain.education.dto.request.EducationRequest;
import efub.ebmt.eeojum.domain.education.dto.response.EducationsResponse;
import efub.ebmt.eeojum.domain.education.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;

    @GetMapping("/search")
    public ResponseEntity<EducationsResponse> getEducation(@RequestBody EducationRequest educationRequest){
        return new ResponseEntity<>(educationService.educationList(educationRequest), HttpStatus.OK);
    }
}
