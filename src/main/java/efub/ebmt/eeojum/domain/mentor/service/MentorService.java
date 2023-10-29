package efub.ebmt.eeojum.domain.mentor.service;

import efub.ebmt.eeojum.domain.mentor.domain.Mentor;
import efub.ebmt.eeojum.domain.mentor.dto.MentorRequest;
import efub.ebmt.eeojum.domain.mentor.dto.MentorResponse;
import efub.ebmt.eeojum.domain.mentor.dto.MentorsResponse;
import efub.ebmt.eeojum.domain.mentor.repository.MentorRepository;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumeResponse;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentorService {
    private final MentorRepository mentorRepository;

    public void addMentor(MentorRequest mentorRequest){
        mentorRepository.save(mentorRequest.of());
    }

    public MentorsResponse findMentorList(){
        return new MentorsResponse(mentorRepository.findAll().stream()
                .map(MentorResponse::new)
                .collect(Collectors.toList()));
    }
}
