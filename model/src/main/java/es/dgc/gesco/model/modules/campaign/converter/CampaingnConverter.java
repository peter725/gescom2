package es.dgc.gesco.model.modules.campaign.converter;

import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.model.modules.campaign.dto.CampaignDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CampaingnConverter {
    @Mapping(target = "typeCampaignId", source = "typeCampaignId")
    CampaignDto convertCampaingnToDto(Campaign campaing);
}