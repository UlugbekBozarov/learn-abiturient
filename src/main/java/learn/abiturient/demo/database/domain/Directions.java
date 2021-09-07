package learn.abiturient.demo.database.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Directions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long direction_id;

    @Setter
    @NotBlank
    @Column(unique = true)
    private String direction_name;

    public Directions(String direction_name) {
        this.direction_name = direction_name;
    }
}
