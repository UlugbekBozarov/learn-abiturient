package learn.abiturient.demo.database.repository;

import learn.abiturient.demo.database.domain.Directions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepo extends JpaRepository<Directions, Long> {
}
