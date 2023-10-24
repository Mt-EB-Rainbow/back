package efub.ebmt.eeojum.domain.question.repository;

import efub.ebmt.eeojum.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Long> {
}
