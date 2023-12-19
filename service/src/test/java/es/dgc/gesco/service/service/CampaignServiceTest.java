package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Import(TestFactory.class)
public class CampaignServiceTest extends AbstractGenericTest {

    @Autowired
    CampaignService campaignService;

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Campaign> campaingnPage = campaignService.getAllByPage(pageable);
    }
}