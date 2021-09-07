package learn.abiturient.demo.Controller;

import learn.abiturient.demo.database.domain.Educations;
import learn.abiturient.demo.exception.ExceptionClass;
import learn.abiturient.demo.exception.error.BadRequest;
import learn.abiturient.demo.requests.NameRequest;
import learn.abiturient.demo.responses.SuccessfulResponse;
import learn.abiturient.demo.services.EducationServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static learn.abiturient.demo.utils.Mappings.EDUCATION;

@RestController
@RequestMapping(path = EDUCATION)
public class EducationController {

    private EducationServices services;

    public EducationController(EducationServices services) {
        this.services = services;
    }

    @GetMapping
    public List<Educations> getEducation() {
        return services.getEducation();
    }

    @PostMapping
    public ResponseEntity<?> createEducation(@Valid @RequestBody NameRequest request, BindingResult result) throws BadRequest {
        ResponseEntity<?> errors = ExceptionClass.getErrors(result);
        if (errors != null) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(services.createEducation(request.getName()), HttpStatus.OK);
    }

    @PutMapping("/{education_id}")
    public ResponseEntity<?> editEducation(@PathVariable Long education_id, @Valid @RequestBody NameRequest request, BindingResult result) throws BadRequest {
        ResponseEntity<?> errors = ExceptionClass.getErrors(result);
        if (errors != null) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(services.editEducation(education_id, request.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/{education_id}")
    public SuccessfulResponse deleteEducation(@PathVariable Long education_id) {
        return services.deleteEducation(education_id);
    }
}
