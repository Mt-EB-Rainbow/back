package efub.ebmt.eeojum.domain.mentor.controller;

import efub.ebmt.eeojum.domain.mentor.dto.MentorRequest;
import efub.ebmt.eeojum.domain.mentor.dto.MentorsResponse;
import efub.ebmt.eeojum.domain.mentor.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mentors")
@RequiredArgsConstructor
public class MentorController {
    private final MentorService mentorService;

    @PostMapping
    public ResponseEntity<String> mentorAdd(@RequestBody MentorRequest mentorRequest) {
        mentorService.addMentor(mentorRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public MentorsResponse mentorList(){
        return mentorService.findMentorList();
    }

}
