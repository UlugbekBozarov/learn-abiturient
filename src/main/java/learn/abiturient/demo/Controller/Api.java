package learn.abiturient.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

    @GetMapping("/")
    public String getA() {
        return "Hello!";
    }
}
