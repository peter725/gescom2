package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.phase.db.entity.PhaseCampaign;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;


@Import(TestFactory.class)
public class PhaseCampaignServiceTest extends AbstractGenericTest {

    @Autowired
    PhaseCampaignService phaseCampaignService;
    @Test
    public void getPhaseCampaignByIdTest(){
        PhaseCampaign phaseCampaign = phaseCampaignService.getPhaseCampaignByid(1L);
    }
}
