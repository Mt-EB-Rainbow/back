package efub.ebmt.eeojum.domain.resume.repository;

import efub.ebmt.eeojum.domain.resume.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findByResumeId(Long resumeId);
}
