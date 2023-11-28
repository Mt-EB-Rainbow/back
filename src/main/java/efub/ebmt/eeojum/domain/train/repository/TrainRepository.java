package efub.ebmt.eeojum.domain.train.repository;

import efub.ebmt.eeojum.domain.train.domain.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
}
