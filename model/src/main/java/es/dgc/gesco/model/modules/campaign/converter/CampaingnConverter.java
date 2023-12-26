package es.dgc.gesco.model.modules.campaign.converter;

import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.model.modules.campaign.dto.CampaignDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CampaingnConverter {
    @Mapping(target = "campaignType", ignore = true)
    CampaignDTO convertCampaingnToDto(Campaign campaing);

    @Mapping(target = "codeCpa", source = "codeCpa")
    Campaign convertDtoToCampaign(CampaignDTO campaignDto);
}