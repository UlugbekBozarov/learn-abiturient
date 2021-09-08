package learn.abiturient.demo.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import learn.abiturient.demo.exception.error.NotAcceptable;
import learn.abiturient.demo.exception.message.ErrorMessage;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long test_id;

    @Setter
    @JsonIgnore
    private Long variant_id;

    @Setter
    private String img;

    @Setter
    private String correct_variant;

    @Setter
    @OneToMany
    @JoinColumn(name = "test_id")
    private List<TestVariant> variants;

    public Test(Long variant_id, String img, String correct_variant) {
        this.variant_id = variant_id;
        this.img = img;
        this.correct_variant = correct_variant;
    }

    public void setVariants(List<TestVariant> variants) {
        if (variants.size() <= 3) {
            this.variants = variants;
        } else {
            ErrorMessage message = new ErrorMessage(406, "Variants size big.");
            throw new NotAcceptable(message);
        }
    }
}
