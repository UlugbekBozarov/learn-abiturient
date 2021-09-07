package learn.abiturient.demo.global_exception;

import learn.abiturient.demo.exception.error.NotFound;
import learn.abiturient.demo.exception.error.SystemError;
import learn.abiturient.demo.exception.error.NotAcceptable;
import learn.abiturient.demo.exception.error.UniqueElement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> system_error(SystemError e) {
        return new ResponseEntity<>(e.getErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> not_found(NotFound e) {
        return new ResponseEntity<>(e.getErrors(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> exception_406(NotAcceptable e) {
        return new ResponseEntity<>(e.getErrors(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<?> unique_element(UniqueElement e) {
        return new ResponseEntity<>(e.getErrors(), HttpStatus.FORBIDDEN);
    }
}
