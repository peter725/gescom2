package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.campaignType.converter.CampaingnTypeConverter;
import es.dgc.gesco.model.modules.campaignType.dto.CampaignTypeDTO;
import es.dgc.gesco.model.modules.campaignType.db.entity.CampaignType;
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

    public Page<CampaignTypeDTO> getAllCampaignType(Pageable pageable) {
        Page<CampaignType> campaignPage = campaignTypeService.getAllByPage(pageable);

        return  loadCampaignTypePageDto(campaignPage);
    }

    private Page<CampaignTypeDTO> loadCampaignTypePageDto(Page<CampaignType> campaignPage) {
        Page<CampaignTypeDTO> campaingTypeDtoPage = campaignPage.map(campaignType -> campaingnTypeConverter.convertCampaingnTypeToDto(campaignType));
        campaingTypeDtoPage.forEach(campaingTypeDto -> {
            CampaignType campaignType = campaignTypeService.getCampaignTypeById(campaingTypeDto.getId());
            campaingTypeDto.setId(campaingTypeDto.getId());
            campaingTypeDto.setName(campaingTypeDto.getName());
        });
        return campaingTypeDtoPage;
    }

}