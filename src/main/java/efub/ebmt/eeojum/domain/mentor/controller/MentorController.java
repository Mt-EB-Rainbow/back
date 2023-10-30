package efub.ebmt.eeojum.domain.mentor.controller;

import efub.ebmt.eeojum.domain.mentor.dto.MentorRequest;
import efub.ebmt.eeojum.domain.mentor.dto.MentorResponse;
import efub.ebmt.eeojum.domain.mentor.dto.MentorsResponse;
import efub.ebmt.eeojum.domain.mentor.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mentors")
@RequiredArgsConstructor
@Tag(name = "멘토 관련 API", description = "멘토 생성, 조회 기능을 제공합니다.")
public class MentorController {
    private final MentorService mentorService;

    @PostMapping
    @Operation(summary = "멘토를 생성합니다.")
    public ResponseEntity<MentorResponse> mentorAdd(@RequestBody MentorRequest mentorRequest) {
        MentorResponse mentorresponse = mentorService.addMentor(mentorRequest);
        return new ResponseEntity<MentorResponse>(HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "멘토 리스트를 조회합니다.")
    public MentorsResponse mentorList(){
        return mentorService.findMentorList();
    }

}
