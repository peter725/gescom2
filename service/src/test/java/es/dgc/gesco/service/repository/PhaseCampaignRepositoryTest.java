package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.phase.db.entity.PhaseCampaign;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class PhaseCampaignRepositoryTest extends AbstractGenericTest {

    @Autowired
    PhaseCampaignRepository phaseCampaignRepository;

    @Test
    public void findApproachById(){
        Optional<PhaseCampaign> phaseCampaign = phaseCampaignRepository.findById(1L);
    }


}