package learn.abiturient.demo.database.repository;

import learn.abiturient.demo.database.domain.TestVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestVariantRepo extends JpaRepository<TestVariant, Long> {
}
