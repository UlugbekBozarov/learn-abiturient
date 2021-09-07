package learn.abiturient.demo.Controller;

import learn.abiturient.demo.database.domain.Directions;
import learn.abiturient.demo.exception.ExceptionClass;
import learn.abiturient.demo.exception.error.BadRequest;
import learn.abiturient.demo.requests.NameRequest;
import learn.abiturient.demo.responses.SuccessfulResponse;
import learn.abiturient.demo.services.DirectionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static learn.abiturient.demo.utils.Mappings.DIRECTION;

@RestController
@RequestMapping(path = DIRECTION)
public class DirectionController {

    private DirectionServices services;

    public DirectionController(DirectionServices services) {
        this.services = services;
    }

    @GetMapping
    public List<Directions> getDirection() {
        return services.getDirection();
    }

    @PostMapping
    public ResponseEntity<?> addDirection(@Valid @RequestBody NameRequest request, BindingResult result) throws BadRequest {
        ResponseEntity<?> errors = ExceptionClass.getErrors(result);
        if (errors != null) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(services.createDirection(request.getName()), HttpStatus.OK);
    }

    @PutMapping("/{direction_id}")
    public ResponseEntity<?> editDirection(@PathVariable Long direction_id, @Valid @RequestBody NameRequest request, BindingResult result) throws BadRequest {
        ResponseEntity<?> errors = ExceptionClass.getErrors(result);
        if (errors != null) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(services.editDirection(direction_id, request.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/{direction_id}")
    public SuccessfulResponse deleteDirection(@PathVariable Long direction_id) {
        return services.deleteDirection(direction_id);
    }
}
