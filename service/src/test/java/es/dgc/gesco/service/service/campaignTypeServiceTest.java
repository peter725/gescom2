package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.CampaignType.db.entity.CampaignType;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(TestFactory.class)
public class campaignTypeServiceTest extends AbstractGenericTest {

    @Autowired
    CampaignTypeService campaignTypeService;

    //@Test
    public void getCampaignTypeByIdTest(){
        CampaignType campaignType = campaignTypeService.getCampaignTypeById(1L);
        assertNotNull(campaignType);
    }



}
