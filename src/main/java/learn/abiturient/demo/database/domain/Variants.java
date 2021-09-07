package learn.abiturient.demo.database.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Variants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variant_id;

    @Setter
    private String title;

    @Setter
    private String variant_owner;

    @Setter
    private Integer worked_number;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Directions direction;

    @Setter
    private Boolean is_pro;

    @OneToMany
    @JoinColumn(name = "variant_id")
    private List<Test> tests;

    public Variants (String title, String variant_owner, Directions direction, Boolean is_pro) {
        this.title = title;
        this.variant_owner = variant_owner;
        this.direction = direction;
        this.is_pro = is_pro;
    }
}
