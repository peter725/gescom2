package es.dgc.gesco.service.service;


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
    NationalAuthorityService nationalAuthorityService;

    @Test
    public void fidAllNationalAuthority(){
        List<NationalAuthority> nationalAuthority = nationalAuthorityService.findAllNationalAuthority();
    }

}