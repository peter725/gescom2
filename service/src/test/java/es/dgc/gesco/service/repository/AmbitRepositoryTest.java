package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.ambit.db.entity.Ambit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class AmbitRepositoryTest extends AbstractGenericTest{

    @Autowired
    AmbitRepository ambitRepository;

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Ambit> ambitPage = ambitRepository.findAll(pageable);
    }

}
