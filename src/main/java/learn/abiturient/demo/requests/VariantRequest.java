package learn.abiturient.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariantRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String variant_owner;

    @NotNull
    private Long direction_id;

    @Getter
    @NotNull
    private Boolean is_pro;
}
