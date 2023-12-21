package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.specialist.db.entity.Specialist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class SpecialistRepositoryTest extends AbstractGenericTest{

    @Autowired
//    SpecialistRepository specialistRepository;

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
//        Page<Specialist> specialistPage = specialistRepository.findAll(pageable);
    }

}