package es.dgc.gesco.model.modules.phase.converter;


import es.dgc.gesco.model.modules.phase.db.entity.PhaseCampaign;
import es.dgc.gesco.model.modules.phase.dto.PhaseCampaignDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhaseConverter {

    PhaseCampaign convertDtoToPhase(PhaseCampaignDTO phaseCampaignDTO);

    PhaseCampaignDTO convertPhaseToDto(PhaseCampaign phaseCampaign);

}
