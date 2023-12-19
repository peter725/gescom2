package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.CampaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.approach.converter.ApproachConverter;
import es.dgc.gesco.model.modules.approach.dto.ApproachDto;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.campaign.converter.CampaingnConverter;
import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.model.modules.campaign.dto.CampaignDto;

import es.dgc.gesco.service.service.AutonomousCommunityService;
import es.dgc.gesco.service.service.CampaignService;
import es.dgc.gesco.service.service.CampaignTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CampaignFacade {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaingnConverter campaingnConverter;

    @Autowired
    private CampaignTypeService campaignTypeService;

    @Autowired
    private AutonomousCommunityService autonomousCommunityService;

    public Page<CampaignDto> getAllCampaign(Pageable pageable) {
        Page<Campaign> campaignPage = campaignService.getAllByPage(pageable);

        return  loadCampaignPageDto(campaignPage);
    }

    private Page<CampaignDto> loadCampaignPageDto(Page<Campaign> campaignPage) {
        Page<CampaignDto> campaingDtoPage = campaignPage.map(campaing -> campaingnConverter.convertCampaingnToDto(campaing));
        campaingDtoPage.forEach(campaingDto -> {
            CampaignType campaignType = campaignTypeService.getCampaignTypeById(campaingDto.getTypeCampaignId().getId());
            AutonomousCommunity autonomousCommunity = autonomousCommunityService.getAutonomousCommunityById(campaingDto.getAutonomousCommunityResponsibleId().getId());
            campaingDto.setAutonomousCommunityResponsibleId(autonomousCommunity);
            campaingDto.setNameCampaign(campaingDto.getNameCampaign());

            // OJOOO!! VERIFICAR EL NOMBRE DE LA CAMPAÑA
        });
        return campaingDtoPage;
    }

}