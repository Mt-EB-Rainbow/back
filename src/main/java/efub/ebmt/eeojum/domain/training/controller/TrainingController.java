package efub.ebmt.eeojum.domain.training.controller;

import efub.ebmt.eeojum.domain.training.domain.Training;
import efub.ebmt.eeojum.domain.training.dto.CourseDto;
import efub.ebmt.eeojum.domain.training.dto.CourseResponseDto;
import efub.ebmt.eeojum.domain.training.repository.TrainingRepository;
import efub.ebmt.eeojum.domain.training.service.TrainingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
@Tag(name = "직업 훈련 받아오는 API", description = "직업 훈련 링크를 xml형태로 받아 json 형태로 반환하는 api입니다.")
public class TrainingController {
//    @Value("${spring.api.authKey}")
//    private String authKey;

    private TrainingRepository trainingRepository;

    @Autowired
    private final TrainingService trainingService;

//    @GetMapping("/{type}")
//    public List<Training> callAPIAndReturnDataAndSave(@PathVariable String type) throws IOException {
//        List<CourseDto> courseDtos = trainingService.callAPIAndReturnData(type);
//        return trainingService.saveCourses(courseDtos);
//    }

    @GetMapping("/{type}")
    public ResponseEntity<List<CourseResponseDto>> getTrainingsByType(@PathVariable String type) throws IOException {
        List<Training> trainings = trainingService.processAndSaveData(type);

        List<CourseResponseDto> courses = trainings.stream()
                .map(CourseResponseDto::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(courses);
    }

}
