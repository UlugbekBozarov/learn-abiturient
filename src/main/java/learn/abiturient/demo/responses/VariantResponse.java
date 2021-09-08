package learn.abiturient.demo.responses;

import learn.abiturient.demo.database.domain.Directions;
import learn.abiturient.demo.database.domain.Test;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VariantResponse {
    private Long variant_id;
    private String title;
    private String variant_owner;
    private Integer worked_number;
    private Directions direction;
    private Boolean is_pro;
    private List<Test> tests;
}
