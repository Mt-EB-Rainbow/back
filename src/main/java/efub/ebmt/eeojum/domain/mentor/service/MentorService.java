package efub.ebmt.eeojum.domain.mentor.service;

import efub.ebmt.eeojum.domain.mentor.dto.MentorDto;

import java.util.List;

public interface MentorService {
    List<MentorDto> registerMentor(MentorDto mentorDTO);
    List<MentorDto> getMentorList();
    List<MentorDto> getTopMatchingMentors(MentorDto userMentorDto, int limit);
}
