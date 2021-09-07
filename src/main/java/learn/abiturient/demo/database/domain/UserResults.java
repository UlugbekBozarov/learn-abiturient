package learn.abiturient.demo.database.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class UserResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long result_id;

    @NotBlank
    private Integer result;
}
