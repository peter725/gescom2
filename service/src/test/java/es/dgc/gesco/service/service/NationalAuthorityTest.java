package es.dgc.gesco.service.service;


import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import es.dgc.gesco.model.modules.nationalAuthority.db.entity.NationalAuthority;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(TestFactory.class)
public class NationalAuthorityTest extends AbstractGenericTest {

    @Autowired
    AuthorityOEUService authorityOEUService;

    @Test
    public void fidAllNationalAuthority(){
        List<AuthorityOEU> authorityOEUList = authorityOEUService.findAllNationalAuthority();
    }

}