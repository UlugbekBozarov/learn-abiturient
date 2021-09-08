package learn.abiturient.demo.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TestVariantRequest {

    @NotBlank
    private String variant;
}
