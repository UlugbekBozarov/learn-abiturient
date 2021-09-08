package learn.abiturient.demo.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TestRequest {

    @NotBlank
    private String img;

    @NotBlank
    private String correct_variant;

//    @NotBlank
    private List<TestVariantRequest> variants;
}
