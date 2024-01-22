package es.consumo.gescom.modules.autonomousCommunityProponent.service;

import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.autonomousCommunityProponent.model.entity.AutonomousCommunityProponentEntity;
import es.consumo.gescom.modules.proponent.model.dto.ProponentDTO;

import java.util.List;

public interface AutonomousCommunityProponentService extends CrudService<AutonomousCommunityProponentEntity, Long>{

    List<ProponentDTO> finByIdCampaign(Long idCampaign);
}
