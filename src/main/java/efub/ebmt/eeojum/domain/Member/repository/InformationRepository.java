package efub.ebmt.eeojum.domain.Member.repository;

import efub.ebmt.eeojum.domain.Member.domain.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
}
