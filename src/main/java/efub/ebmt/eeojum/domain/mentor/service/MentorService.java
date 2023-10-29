package efub.ebmt.eeojum.domain.mentor.service;

import efub.ebmt.eeojum.domain.mentor.dto.MentorRequest;
import efub.ebmt.eeojum.domain.mentor.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentorService {
    private final MentorRepository mentorRepository;

    public void addMentor(MentorRequest mentorRequest){
        mentorRepository.save(mentorRequest.of());
    }
}
