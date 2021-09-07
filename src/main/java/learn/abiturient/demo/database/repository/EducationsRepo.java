package learn.abiturient.demo.database.repository;

import learn.abiturient.demo.database.domain.Educations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationsRepo extends JpaRepository<Educations, Long> {
}
