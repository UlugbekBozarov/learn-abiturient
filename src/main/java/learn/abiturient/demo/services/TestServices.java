package learn.abiturient.demo.services;

import learn.abiturient.demo.database.domain.Test;
import learn.abiturient.demo.database.domain.TestVariant;
import learn.abiturient.demo.database.domain.Variants;
import learn.abiturient.demo.database.repository.TestRepo;
import learn.abiturient.demo.database.repository.TestVariantRepo;
import learn.abiturient.demo.database.repository.VariantsRepo;
import learn.abiturient.demo.exception.error.NotAcceptable;
import learn.abiturient.demo.exception.error.NotFound;
import learn.abiturient.demo.exception.error.SystemError;
import learn.abiturient.demo.exception.message.ErrorMessage;
import learn.abiturient.demo.requests.TestRequest;
import learn.abiturient.demo.requests.TestVariantRequest;
import learn.abiturient.demo.responses.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServices {

    private TestRepo testRepo;
    private TestVariantRepo testVariantRepo;
    private VariantsRepo variantsRepo;

    @Autowired
    public TestServices(TestRepo testRepo, TestVariantRepo testVariantRepo, VariantsRepo variantsRepo) {
        this.testRepo = testRepo;
        this.testVariantRepo = testVariantRepo;
        this.variantsRepo = variantsRepo;
    }

    public List<Test> getTests() throws SystemError {
        try {
            return testRepo.findAll();
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(500, "Interval server error");
            throw new SystemError(message);
        }
    }

    public SuccessfulResponse createTest(Long variant_id, TestRequest request) {
        isVariantLength(request.getVariants());
        isTestLength(variant_id);
        try {
            Test test = new Test(variant_id, request.getImg(), request.getCorrect_variant());
            List<TestVariant> variants = new ArrayList<>();
            for (int i = 0; i < request.getVariants().size(); i++) {
                variants.add(new TestVariant(request.getVariants().get(i).getVariant()));
            }
            testVariantRepo.saveAll(variants);
            test.setVariants(variants);
            testRepo.save(test);
            return new SuccessfulResponse(200, "Test created.");
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(500, "Test not created.");
            throw new NotAcceptable(message);
        }
    }

    private boolean isVariantLength(List<TestVariantRequest> variants) {
        if (variants.size() <= 3) {
            return true;
        } else {
            ErrorMessage message = new ErrorMessage(406, "Variants length big.");
            throw new NotAcceptable(message);
        }
    }

    private boolean isTestLength(Long variant_id) throws NotFound {
        boolean vb = false;
        try {
            if (variantsRepo.findById(variant_id).get().getTests().size() < 30) {
                return true;
            } else {
                vb = true;
                ErrorMessage message = new ErrorMessage(406, "This variant tests length big.");
                throw new NotAcceptable(message);
            }
        } catch (Exception e) {
            if (vb) {
                ErrorMessage message = new ErrorMessage(406, "This variant tests length big.");
                throw new NotAcceptable(message);
            } else {
                ErrorMessage message = new ErrorMessage(404, "Variant not fount.");
                throw new NotFound(message);
            }
        }
    }
}
