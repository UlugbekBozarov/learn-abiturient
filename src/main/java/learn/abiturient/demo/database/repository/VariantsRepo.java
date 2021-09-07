package learn.abiturient.demo.database.repository;

import learn.abiturient.demo.database.domain.Variants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantsRepo extends JpaRepository<Variants, Long> {
}
