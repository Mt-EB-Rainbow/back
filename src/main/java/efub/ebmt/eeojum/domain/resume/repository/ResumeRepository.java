package efub.ebmt.eeojum.domain.resume.repository;

import efub.ebmt.eeojum.domain.resume.domain.Resume;
import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByMemberId(Long memberId);
    List<Resume> findByResumeStatus(ResumeStatus resumeStatus);
}
