package learn.abiturient.demo.database.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotBlank
    private String fullname;

    private String location;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Directions direction_id;

    @ManyToOne
    @JoinColumn(name = "education_id")
    private Educations education_id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<UserResults> results;

}
