package efub.ebmt.eeojum.domain.mentor.controller;

import efub.ebmt.eeojum.domain.mentor.dto.MentorDto;
import efub.ebmt.eeojum.domain.mentor.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentors")
public class MentorController {
    private final MentorService mentorService;

    @Autowired
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping("/register")
    public List<MentorDto> registerMentor(@RequestBody MentorDto mentorDTO) {
        return mentorService.registerMentor(mentorDTO);
    }

    @GetMapping("/list")
    public List<MentorDto> getMentorList() {
        return mentorService.getMentorList();
    }
}
