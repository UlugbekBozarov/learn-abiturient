package learn.abiturient.demo.requests;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VariantsByDirectionRequest {

    private Long variant_id;

    private String title;

    private String variant_owner;

    private Integer worked_number;

    private Boolean is_pro;
}
