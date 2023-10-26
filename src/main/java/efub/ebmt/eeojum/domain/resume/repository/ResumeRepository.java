package efub.ebmt.eeojum.domain.resume.repository;

import efub.ebmt.eeojum.domain.resume.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
