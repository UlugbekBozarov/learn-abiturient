package learn.abiturient.demo.exception.message;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private int code;
    private String message;
}
