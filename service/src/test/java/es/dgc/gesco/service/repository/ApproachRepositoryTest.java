package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.user.db.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ApproachRepositoryTest extends AbstractGenericTest {

    @Autowired
    ApproachRepository approachRepository;

//    @Test
    public void getAllApproach(){
        List<Approach> approaches = approachRepository.findAll();
    }

    @Test
    public void findApproachById(){
        Optional<Approach> approach = approachRepository.findById(1L);
    }
}
