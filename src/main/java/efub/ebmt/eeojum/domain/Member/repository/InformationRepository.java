package efub.ebmt.eeojum.Member.repository;

import efub.ebmt.eeojum.Member.domain.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
}
