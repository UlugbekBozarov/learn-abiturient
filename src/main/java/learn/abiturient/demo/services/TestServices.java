package learn.abiturient.demo.services;

import learn.abiturient.demo.database.domain.Test;
import learn.abiturient.demo.database.repository.TestRepo;
import learn.abiturient.demo.exception.error.SystemError;
import learn.abiturient.demo.exception.message.ErrorMessage;
import learn.abiturient.demo.responses.SuccessfulResponse;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServices {

    private TestRepo testRepo;

    public TestServices(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    public List<Test> getTests() throws SystemError {
        try {
            return testRepo.findAll();
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(500, "Interval server error");
            throw new SystemError(message);
        }
    }

    public SuccessfulResponse createTest() {
        try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
