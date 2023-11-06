package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutonomousCommunityRepositoryTest extends AbstractGenericTest{

    @Autowired
    AutonomousCommunityRepository autonomousCommunityRepository;

    @Test
    public void getAllTest(){
        List<AutonomousCommunity> autonomousCommunityList = autonomousCommunityRepository.findAll();
        assertEquals(1, autonomousCommunityList.size());
    }
}
