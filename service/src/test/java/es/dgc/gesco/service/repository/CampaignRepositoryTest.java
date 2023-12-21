package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class CampaignRepositoryTest extends AbstractGenericTest {

    @Autowired
    CampaignRepository campaignRepository;

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Campaign> campaingnPage = campaignRepository.findAll(pageable);
    }
}
