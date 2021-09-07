package learn.abiturient.demo.Controller;

import learn.abiturient.demo.database.domain.Variants;
import learn.abiturient.demo.database.repository.VariantsRepo;
import learn.abiturient.demo.exception.ExceptionClass;
import learn.abiturient.demo.exception.error.BadRequest;
import learn.abiturient.demo.requests.VariantRequest;
import learn.abiturient.demo.responses.SuccessfulResponse;
import learn.abiturient.demo.services.VariantServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static learn.abiturient.demo.utils.Mappings.VARIANT;

@RestController
@RequestMapping(path = VARIANT)
public class VariantController {

    private VariantServices services;

    public VariantController(VariantServices services) {
        this.services = services;
    }

    @GetMapping
    public List<Variants> getVariants() {
        return services.getVariants();
    }

    @PostMapping
    public ResponseEntity<?> createVariant(@Valid @RequestBody VariantRequest request, BindingResult result) throws BadRequest {
        ResponseEntity<?> errors = ExceptionClass.getErrors(result);
        if (errors != null) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(services.createVariant(request), HttpStatus.OK);
    }

    @PutMapping(path = "/{variant_id}")
    public ResponseEntity<?> editVariant(@PathVariable Long variant_id, @Valid @RequestBody VariantRequest request, BindingResult result) throws BadRequest {
        ResponseEntity<?> errors = ExceptionClass.getErrors(result);
        if (errors != null) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(services.editVariant(variant_id, request), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{variant_id}")
    public SuccessfulResponse deleteVariant(@PathVariable Long variant_id) {
        return services.deleteVariant(variant_id);
    }
}
