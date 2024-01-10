package es.consumo.junta_arbitral.modules.autonomousCommunity.service;

import es.consumo.junta_arbitral.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.junta_arbitral.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import org.springframework.data.domain.Page;

import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;

public interface AutonomousCommunityService extends CrudService<AutonomousCommunityEntity, Long>{

    Page<AutonomousCommunityEntity.SimpleProjection> findAllAutonomousCommunityByName(CriteriaWrapper<AutonomousCommunityCriteria> wrapper, String name);
    
}
