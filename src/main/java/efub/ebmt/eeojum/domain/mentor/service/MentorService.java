package efub.ebmt.eeojum.domain.mentor.service;

import efub.ebmt.eeojum.domain.mentor.domain.Mentor;
import efub.ebmt.eeojum.domain.mentor.dto.MentorRequest;
import efub.ebmt.eeojum.domain.mentor.dto.MentorResponse;
import efub.ebmt.eeojum.domain.mentor.dto.MentorsResponse;
import efub.ebmt.eeojum.domain.mentor.repository.MentorRepository;
import efub.ebmt.eeojum.global.exception.CustomException;
import efub.ebmt.eeojum.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentorService {
    private final MentorRepository mentorRepository;

    public MentorResponse addMentor(MentorRequest mentorRequest){
        if(mentorRequest.getEmail() == null && mentorRequest.getPhoneNumber() == null && mentorRequest.getKakaoUserid() == null)
            throw new CustomException(ErrorCode.MENTOR_BAD_REQUEST);
        Mentor mentor = mentorRepository.save(mentorRequest.of());
        return new MentorResponse(mentor);
    }

    public MentorsResponse findMentorList(){
        return new MentorsResponse(mentorRepository.findAll().stream()
                .map(MentorResponse::new)
                .collect(Collectors.toList()));
    }
}
