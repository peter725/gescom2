package es.consumo.gescom.modules.autonomousCommunitySpecialist.service;

import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.autonomousCommunitySpecialist.model.entity.AutonomousCommunitySpecialistEntity;
import es.consumo.gescom.modules.specialist.model.dto.SpecialistDTO;

import java.util.List;

public interface AutonomousCommunitySpecialistService extends CrudService<AutonomousCommunitySpecialistEntity, Long>{
    List<SpecialistDTO> finByIdCampaign(Long idCampaign);
}
