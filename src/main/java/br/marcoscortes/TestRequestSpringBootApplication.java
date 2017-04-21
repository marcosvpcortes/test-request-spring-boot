package br.marcoscortes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController()
public class TestRequestSpringBootApplication {

    @Autowired
    private TestingObjectService testingObjectService;

    @GetMapping("/test")
    public int test() {
        return testingObjectService.processTestingObjects();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestRequestSpringBootApplication.class, args);
    }

}
