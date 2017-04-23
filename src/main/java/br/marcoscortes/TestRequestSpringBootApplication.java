package br.marcoscortes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController()
public class TestRequestSpringBootApplication {

    @Autowired
    private TestingObjectService testingObjectService;

    private static Logger logger = LoggerFactory.getLogger(TestRequestSpringBootApplication.class);
    
    @GetMapping("/test")
    public int test() {
        return testingObjectService.processTestingObjects();
    }

    public static void main(String[] args) {
        // Get current size of heap in bytes
        long heapSize = Runtime.getRuntime().totalMemory()/(1024l*1024l);

// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory()/(1024l*1024l);

 // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        long heapFreeSize = Runtime.getRuntime().freeMemory()/(1024l*1024l);
        
        logger.info("heapSize: {}", heapSize);
        logger.info("heapMaxSize: {}", heapMaxSize);
        logger.info("heapFreeSize: {}", heapFreeSize);
        SpringApplication.run(TestRequestSpringBootApplication.class, args);
    }

}
