package efub.ebmt.eeojum.domain.diagnosis.repository;

import efub.ebmt.eeojum.domain.diagnosis.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
