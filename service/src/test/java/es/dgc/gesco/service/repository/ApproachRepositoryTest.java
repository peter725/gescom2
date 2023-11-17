package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ApproachRepositoryTest extends AbstractGenericTest {

    @Autowired
    ApproachRepository approachRepository;

//    @Test
    public void getAllApproach(){
        List<Approach> approaches = approachRepository.findAll();
    }

//    @Test
    public void findApproachById(){
        Optional<Approach> approach = approachRepository.findById(1L);
    }

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Approach> approachPage = approachRepository.findAll(pageable);
//        assertEquals(1, approachPage.getTotalElements());
    }
}
