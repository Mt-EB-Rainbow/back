package efub.ebmt.eeojum.domain.example.repository;

import efub.ebmt.eeojum.domain.example.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {
}
