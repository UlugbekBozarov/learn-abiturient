package learn.abiturient.demo.exception.error;

import learn.abiturient.demo.exception.message.ErrorMessage;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemError extends RuntimeException {
    private ErrorMessage errors;
}
