package learn.abiturient.demo.database.repository;

import learn.abiturient.demo.database.domain.UserResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResultsRepo extends JpaRepository<UserResults, Long> {
}
