package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.service.util.TestFactory;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestFactory.class)
public class AutonomousCommunityServiceTest extends AbstractGenericTest{

    @Autowired
    AutonomousCommunityService autonomousCommunityService;

    @Test
    public void getAllTest(){
        List<AutonomousCommunity> autonomousCommunityList = autonomousCommunityService.getAll();
        assertEquals(1, autonomousCommunityList.size());
    }


}
