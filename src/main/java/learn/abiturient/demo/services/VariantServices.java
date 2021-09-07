package learn.abiturient.demo.services;

import learn.abiturient.demo.database.domain.Directions;
import learn.abiturient.demo.database.domain.Variants;
import learn.abiturient.demo.database.repository.DirectionRepo;
import learn.abiturient.demo.database.repository.VariantsRepo;
import learn.abiturient.demo.exception.error.NotFound;
import learn.abiturient.demo.exception.error.SystemError;
import learn.abiturient.demo.exception.message.ErrorMessage;
import learn.abiturient.demo.requests.VariantRequest;
import learn.abiturient.demo.responses.SuccessfulResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServices {

    private VariantsRepo variantsRepo;
    private DirectionRepo directionRepo;

    public VariantServices(VariantsRepo variantsRepo) {
        this.variantsRepo = variantsRepo;
    }

    public List<Variants> getVariants() throws SystemError {
        try {
            return variantsRepo.findAll();
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(500, "Interval server error");
            throw new SystemError(message);
        }
    }

    public SuccessfulResponse createVariant(VariantRequest request) throws SystemError {

        try {
            Variants variant = new Variants(request.getTitle(), request.getVariant_owner(), isDirections(request.getDirection_id()), request.getIs_pro());
            variantsRepo.save(variant);
            return new SuccessfulResponse(200, "Variant created.");
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(500, "Interval server error");
            throw new SystemError(message);
        }
    }

    public SuccessfulResponse editVariant(Long variant_id, VariantRequest request) throws NotFound {
        try {
            Variants variant = variantsRepo.findById(variant_id).get();
            variant.setTitle(request.getTitle());
            variant.setVariant_owner(request.getVariant_owner());
            variant.setIs_pro(request.getIs_pro());
            return new SuccessfulResponse(200, "Variant created.");
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(404, "Nothing found in this ID.");
            throw new NotFound(message);
        }
    }

    public SuccessfulResponse deleteVariant(Long variant_id) {
        try {
            variantsRepo.deleteById(variant_id);
            return new SuccessfulResponse(200, "Variant deteted.");
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(404, "Nothing found in this ID.");
            throw new NotFound(message);
        }
    }

    private Directions isDirections(Long direction_id) throws NotFound {
        try {
            Directions direction = directionRepo.findById(direction_id).get();
            if (direction != null) {
                return direction;
            }
            ErrorMessage message = new ErrorMessage(404, "Not found");
            throw new NotFound(message);
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(500, "Interval server error");
            throw new SystemError(message);
        }
    }
}
