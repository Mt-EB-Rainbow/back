package efub.ebmt.eeojum.domain.train.controller;

import efub.ebmt.eeojum.domain.train.dto.request.TrainRequest;
import efub.ebmt.eeojum.domain.train.dto.response.TrainsResponse;
import efub.ebmt.eeojum.domain.train.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class TrainController {
    private final TrainService educationService;

    @GetMapping("/search")
    public ResponseEntity<TrainsResponse> getEducation(@RequestBody TrainRequest trainRequest){
        return new ResponseEntity<>(educationService.educationList(trainRequest), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveEducation(){
        educationService.educationSave();
        return new ResponseEntity<>("저장 완료", HttpStatus.OK);
    }

}
