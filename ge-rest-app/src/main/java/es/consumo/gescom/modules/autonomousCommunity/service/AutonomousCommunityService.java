package es.consumo.gescom.modules.autonomousCommunity.service;

import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;

import java.util.List;

public interface AutonomousCommunityService extends CrudService<AutonomousCommunityEntity, Long>{

    Page<AutonomousCommunityEntity.SimpleProjection> findAllAutonomousCommunityByName(CriteriaWrapper<AutonomousCommunityCriteria> wrapper, String name);

}
