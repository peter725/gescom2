package es.dgc.gesco.model.modules.CampaignType.converter;

import es.dgc.gesco.model.modules.CampaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.CampaignType.dto.CampaignTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CampaingnTypeConverter {
    @Mapping(target = "id", source = "id")
    CampaignTypeDto convertCampaingnTypeToDto(CampaignType campaignType);
}