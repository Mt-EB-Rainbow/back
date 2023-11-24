package efub.ebmt.eeojum.domain.example.repository;

import efub.ebmt.eeojum.domain.example.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExampleRepository extends JpaRepository<Example, Long> {
    Optional<Example> findByExampleId(Long exampleId);
}
