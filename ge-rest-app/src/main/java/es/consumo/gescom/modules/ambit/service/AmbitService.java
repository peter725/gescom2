package es.consumo.gescom.modules.ambit.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import org.springframework.data.domain.Page;

public interface AmbitService extends CrudService<AmbitEntity, Long>{

    Page<AmbitEntity.SimpleProjection> findAllAmbitById(CriteriaWrapper<AmbitCriteria> wrapper, Long id);
    
}
