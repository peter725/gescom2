package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.campaignType.db.entity.CampaignType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CampaignTypeRepositoryTest extends AbstractGenericTest{

    @Autowired
    CampaignTypeRepository campaignTypeRepository;

    @Test
    public void getByIdTest(){
        CampaignType campaingnType = campaignTypeRepository.findById(1L).get();
        assertNotNull(campaingnType);
    }

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<CampaignType> campaingnTypePage = campaignTypeRepository.findAll(pageable);
    }

}
