package learn.abiturient.demo.Controller;

import learn.abiturient.demo.database.domain.Test;
import learn.abiturient.demo.exception.ExceptionClass;
import learn.abiturient.demo.exception.error.BadRequest;
import learn.abiturient.demo.requests.TestRequest;
import learn.abiturient.demo.responses.SuccessfulResponse;
import learn.abiturient.demo.services.TestServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static learn.abiturient.demo.utils.Mappings.TEST;

@RestController
@RequestMapping(path = TEST)
public class TestController {

    private TestServices services;

    public TestController(TestServices services) {
        this.services = services;
    }

    @GetMapping
    public List<Test> getTests() {
        return services.getTests();
    }

    @PostMapping(path = "/{variant_id}")
    public ResponseEntity<?> createTest(@PathVariable Long variant_id, @Valid @RequestBody TestRequest request, BindingResult result) throws BadRequest {

        ResponseEntity<?> errors = ExceptionClass.getErrors(result);
        if (errors != null) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(services.createTest(variant_id, request), HttpStatus.OK);
    }
}
