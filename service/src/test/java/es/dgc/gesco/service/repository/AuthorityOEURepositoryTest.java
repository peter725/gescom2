package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorityOEURepositoryTest  extends AbstractGenericTest{

    @Autowired
    AuthorityOEURepository authorityOEURepository;

    //@Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<AuthorityOEU> authorityOEUPage = authorityOEURepository.findAll(pageable);
        assertEquals(1, authorityOEUPage.getTotalElements());
    }
}
