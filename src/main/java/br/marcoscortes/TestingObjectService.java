package br.marcoscortes;

import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestingObjectService {

    @Value("${test.object.size}")
    private int objectSize;

    @Value("${test.object.count}")
    private int objectCount;
    
    @Value("${test.delayByObject}")
    private long delayByObject;
    
    private Logger logger = LoggerFactory.getLogger(TestingObjectService.class);

    public int processTestingObjects() {
        List<TestingObject> list = IntStream.range(0, objectCount)
                .mapToObj(i -> new TestingObject(objectSize))
                .collect(toList());

        List<Integer> listHashs = list.stream()
        .map(to -> {
            try{
                Thread.sleep(delayByObject);
            }catch(InterruptedException e){
                logger.error("Houve erro ao dar delay no objeto", e);
            }
            return to.hashCode();
        })
        .collect(toList());
        
        return listHashs.hashCode();
    }
}
