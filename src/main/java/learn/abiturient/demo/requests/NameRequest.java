package learn.abiturient.demo.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NameRequest {

    @NotBlank
    private String name;
}
