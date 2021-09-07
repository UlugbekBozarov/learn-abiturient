package learn.abiturient.demo.responses;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessfulResponse {

    @NotNull
    private Integer code;

    @NotBlank
    private String message;
}
