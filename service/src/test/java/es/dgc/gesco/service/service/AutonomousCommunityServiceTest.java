package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.service.util.TestFactory;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestFactory.class)
public class AutonomousCommunityServiceTest extends AbstractGenericTest{

    @Autowired
    AutonomousCommunityService autonomousCommunityService;

    //@Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<AutonomousCommunity> autonomousCommunityPage = autonomousCommunityService.getAllPage(pageable);
        assertEquals(1, autonomousCommunityPage.getTotalElements());
    }


}
