package efub.ebmt.eeojum.domain.training.repository;

import efub.ebmt.eeojum.domain.training.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByType(String type);
    Training findByCourseSeq(String courseSeq);
}
