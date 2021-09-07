package learn.abiturient.demo.services;

import learn.abiturient.demo.database.domain.Directions;
import learn.abiturient.demo.database.domain.Educations;
import learn.abiturient.demo.database.repository.EducationsRepo;
import learn.abiturient.demo.exception.error.NotAcceptable;
import learn.abiturient.demo.exception.error.NotFound;
import learn.abiturient.demo.exception.error.SystemError;
import learn.abiturient.demo.exception.error.UniqueElement;
import learn.abiturient.demo.exception.message.ErrorMessage;
import learn.abiturient.demo.responses.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServices {

    private EducationsRepo educationsRepo;

    @Autowired
    public EducationServices(EducationsRepo educationsRepo) {
        this.educationsRepo = educationsRepo;
    }

    public List<Educations> getEducation() throws SystemError {
        try {
            return educationsRepo.findAll();
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(500, "Interval server error");
            throw new SystemError(message);
        }
    }

    public SuccessfulResponse createEducation(String name) throws NotAcceptable {
        equalUnique(name);
        try {
            Educations education = new Educations(name);
            educationsRepo.save(education);
            return new SuccessfulResponse(200, "New education created.");
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(406, "Education not created.");
            throw new NotAcceptable(message);
        }
    }

    public SuccessfulResponse editEducation(Long education_id, String new_education_name) throws NotAcceptable {
        equalUnique(education_id, new_education_name);
        try {
            Educations direction = educationsRepo.findById(education_id).get();
            direction.setEducation_name(new_education_name);
            educationsRepo.save(direction);
            return new SuccessfulResponse(200, "Education edited.");
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(404, "Nothing found in this ID.");
            throw new NotFound(message);
        }
    }

    public SuccessfulResponse deleteEducation(Long education_id) throws NotFound {
        try {
            educationsRepo.deleteById(education_id);
            return new SuccessfulResponse(200, "Education deleted.");
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(404, "Nothing found in this ID..");
            throw new NotFound(message);
        }
    }


    private boolean equalUnique(String name) throws UniqueElement {
        try {
            List<Educations> list = educationsRepo.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getEducation_name().equals(name)) {
                    ErrorMessage message = new ErrorMessage(403, "Unique element");
                    throw new NotAcceptable(message);
                }
            }
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(403, "Unique element");
            throw new NotAcceptable(message);
        }
        return true;
    }

    private boolean equalUnique(Long id, String name) {
        try {
            List<Educations> list = educationsRepo.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getEducation_name().equals(name) && list.get(i).getEducation_id() != id) {
                    ErrorMessage message = new ErrorMessage(403, "Unique element");
                    throw new NotAcceptable(message);
                }
            }
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(403, "Unique element");
            throw new NotAcceptable(message);
        }
        return true;
    }
}
