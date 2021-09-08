package learn.abiturient.demo.database.repository;

import learn.abiturient.demo.database.domain.Directions;
import learn.abiturient.demo.database.domain.Variants;
import learn.abiturient.demo.requests.VariantsByDirectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantsRepo extends JpaRepository<Variants, Long> {
    List<Variants> findAllByDirection(Directions direction);
}
