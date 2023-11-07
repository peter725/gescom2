package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.authorityDGC.db.entity.AuthorityDGC;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestFactory.class)
public class AuthorityDGCServiceTest extends AbstractGenericTest {

    @Autowired
    AuthorityDGCService authorityDGCService;

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<AuthorityDGC> authorityDGCPage = authorityDGCService.getAllPage(pageable);
        assertEquals(1, authorityDGCPage.getTotalElements());
    }


}