package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.campaignType.db.entity.CampaignType;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(TestFactory.class)
public class CampaignTypeServiceTest extends AbstractGenericTest {

    @Autowired
    CampaignTypeService campaignTypeService;

    @Test
    public void getCampaignTypeByIdTest(){
        CampaignType campaignType = campaignTypeService.getCampaignTypeById(1L);
        assertNotNull(campaignType);
    }

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<CampaignType> campaingnTypePage = campaignTypeService.getAllByPage(pageable);
    }


}
