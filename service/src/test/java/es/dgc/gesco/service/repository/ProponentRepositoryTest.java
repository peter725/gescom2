package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ProponentRepositoryTest extends AbstractGenericTest{

    @Autowired
    ProponentRepository proponentRepository;

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Proponent> proponentPage = proponentRepository.findAll(pageable);
    }

}