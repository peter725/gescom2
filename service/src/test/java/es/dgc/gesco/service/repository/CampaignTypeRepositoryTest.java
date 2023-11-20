package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.CampaignType.db.entity.CampaignType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CampaignTypeRepositoryTest extends AbstractGenericTest{

    @Autowired
    CampaignTypeRepository campaignTypeRepository;

    @Test
    public void getByIdTest(){
        CampaignType campaingnType = campaignTypeRepository.findById(1L).get();
        assertNotNull(campaingnType);
    }

}
