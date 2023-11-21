package efub.ebmt.eeojum.domain.dictionary.repository;

import efub.ebmt.eeojum.domain.dictionary.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
