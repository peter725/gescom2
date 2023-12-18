package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutonomousCommunityRepositoryTest extends AbstractGenericTest{

    @Autowired
    AutonomousCommunityRepository autonomousCommunityRepository;

    //@Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<AutonomousCommunity> autonomousCommunityPage = autonomousCommunityRepository.findAll(pageable);
        assertEquals(1, autonomousCommunityPage.getTotalElements());
    }
}
