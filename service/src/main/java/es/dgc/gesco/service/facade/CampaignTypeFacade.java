package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.CampaignType.converter.CampaingnTypeConverter;
import es.dgc.gesco.model.modules.CampaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.CampaignType.dto.CampaignTypeDto;
import es.dgc.gesco.service.service.CampaignTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CampaignTypeFacade {

    @Autowired
    private CampaignTypeService campaignTypeService;

    @Autowired
    private CampaingnTypeConverter campaingnTypeConverter;

    public Page<CampaignTypeDto> getAllCampaignType(Pageable pageable) {
        Page<CampaignType> campaignPage = campaignTypeService.getAllByPage(pageable);

        return  loadCampaignTypePageDto(campaignPage);
    }

    private Page<CampaignTypeDto> loadCampaignTypePageDto(Page<CampaignType> campaignPage) {
        Page<CampaignTypeDto> campaingTypeDtoPage = campaignPage.map(campaignType -> campaingnTypeConverter.convertCampaingnTypeToDto(campaignType));
        campaingTypeDtoPage.forEach(campaingTypeDto -> {
            CampaignType campaignType = campaignTypeService.getCampaignTypeById(campaingTypeDto.getId());
            campaingTypeDto.setId(campaingTypeDto.getId());
            campaingTypeDto.setType(campaingTypeDto.getType());
        });
        return campaingTypeDtoPage;
    }

}