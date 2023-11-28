package efub.ebmt.eeojum.domain.diagnosis.repository;

import efub.ebmt.eeojum.domain.diagnosis.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByQuestionId(Long questionId);
}
