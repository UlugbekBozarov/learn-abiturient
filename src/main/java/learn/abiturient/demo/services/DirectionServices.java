package learn.abiturient.demo.services;

import learn.abiturient.demo.database.domain.Directions;
import learn.abiturient.demo.database.repository.DirectionRepo;
import learn.abiturient.demo.exception.error.NotFound;
import learn.abiturient.demo.exception.error.SystemError;
import learn.abiturient.demo.exception.error.NotAcceptable;
import learn.abiturient.demo.exception.error.UniqueElement;
import learn.abiturient.demo.exception.message.ErrorMessage;
import learn.abiturient.demo.responses.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectionServices {

    private DirectionRepo directionRepo;

    @Autowired
    public DirectionServices(DirectionRepo directionRepo) {
        this.directionRepo = directionRepo;
    }

    public List<Directions> getDirection() throws SystemError {
        try {
            return directionRepo.findAll();
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(500, "Interval server error");
            throw new SystemError(message);
        }
    }

    public SuccessfulResponse createDirection(String name) throws NotAcceptable {
        equalUnique(name);
        try {
            Directions direction = new Directions(name);
            directionRepo.save(direction);
            return new SuccessfulResponse(200, "New direction created.");
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(406, "Direction not created.");
            throw new NotAcceptable(message);
        }
    }

    public SuccessfulResponse editDirection(Long direction_id, String new_direction_name) throws NotFound {
        try {
            Directions direction = directionRepo.findById(direction_id).get();
            equalUnique(direction_id, new_direction_name);

            direction.setDirection_name(new_direction_name);
            directionRepo.save(direction);
            return new SuccessfulResponse(200, "Direction edited.");
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(404, "Nothing found in this ID.");
            throw new NotFound(message);
        }
    }

    public SuccessfulResponse deleteDirection(Long direction_id) throws NotFound {
        try {
            directionRepo.deleteById(direction_id);
            return new SuccessfulResponse(200, "Direction deleted.");
        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage(404, "Nothing found in this ID.");
            throw new NotFound(message);
        }
    }


    private boolean equalUnique(String name) throws UniqueElement {
        try {
            List<Directions> list = directionRepo.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDirection_name().equals(name)) {
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
            List<Directions> list = directionRepo.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDirection_name().equals(name) && list.get(i).getDirection_id() != id) {
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
