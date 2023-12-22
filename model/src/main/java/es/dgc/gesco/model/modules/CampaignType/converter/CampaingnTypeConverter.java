package es.dgc.gesco.model.modules.campaignType.converter;

import es.dgc.gesco.model.modules.campaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.CampaignType.dto.CampaignTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CampaingnTypeConverter {
    @Mapping(target = "id", source = "id")
    CampaignTypeDTO convertCampaingnTypeToDto(CampaignType campaignType);

    CampaignType convertDtoToCampaingnType(CampaignTypeDTO campaignTypeDto);
}