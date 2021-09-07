package learn.abiturient.demo.database.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Educations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long education_id;

    @Setter
    @NotBlank
    private String education_name;

    public Educations(String education_name) {
        this.education_name = education_name;
    }
}
