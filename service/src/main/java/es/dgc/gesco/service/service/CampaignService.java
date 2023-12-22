package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.service.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CampaignService {


    private final CampaignRepository campaignRepository;

    public Page<Campaign> getAllByPage(Pageable pageable) {
        Page<Campaign> campaignPage = campaignRepository.findAll(pageable);
        return campaignPage;
    }

    public Campaign saveCampaign(Campaign campaign) {
        Campaign newCampaign = campaignRepository.save(campaign);
        return newCampaign;
    }

}