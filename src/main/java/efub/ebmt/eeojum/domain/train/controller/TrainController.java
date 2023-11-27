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

    @PostMapping("/search")
    public ResponseEntity<TrainsResponse> getEducationSearch(@RequestBody TrainRequest trainRequest){
        return new ResponseEntity<>(educationService.educationSearchList(trainRequest), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveEducation(){
        return new ResponseEntity<>("저장 불가", HttpStatus.OK);
        //educationService.educationSave();
        //return new ResponseEntity<>("저장 완료", HttpStatus.OK);
    }

    @GetMapping("/search/{jobId}")
    public ResponseEntity<TrainsResponse> getEducationSearchByJobId(@PathVariable Long jobId){
        return new ResponseEntity<>(educationService.educationSearchByJobId(jobId), HttpStatus.OK);
    }

}
