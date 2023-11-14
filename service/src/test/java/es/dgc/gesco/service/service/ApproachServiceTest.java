package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Import(TestFactory.class)
public class ApproachServiceTest extends AbstractGenericTest {

    @Autowired
    ApproachService approachService;

   // @Test
    public void saveApproach(){
        Approach approach = new Approach();
        approach.setSent(true);
        approach.setId_autonomous_community(1L);
        //approach.setUser();
        approach.setIdCampaignType(1L);
        approach.setDate(LocalDateTime.now());
        approach.setApproach("Prupuesta 1");
        approach.setJustification("Justificacion 1");
        approach.setObjective("Objetivo 1");
        approach.setViability("Viavilidad 1");
        approach.setCreatedAt(LocalDateTime.now());
        approach.setCreatedBy(1L);
        approach.setUpdatedAt(LocalDateTime.now());
        approach.setUpdatedBy(1L);


        approach = approachService.saveApproach(approach);

        assertNotNull(approach.getId());
    }

    @Test
    public void fidAllApproach(){
        List<Approach> approaches = approachService.findAllApproach();
    }

   // @Test
    public void getApproachByIdTest(){
        Approach approach = approachService.getApproachById(5L);
        assertNotNull(approach);
    }

}