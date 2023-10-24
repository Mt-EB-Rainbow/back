package efub.ebmt.eeojum.domain.resume.repository;

import efub.ebmt.eeojum.domain.resume.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
