package es.consumo.gescom.modules.arbiter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.arbiter.model.criteria.CollegiateTypeCriteria;
import es.consumo.gescom.modules.arbiter.model.entity.CollegiateTypeEntity;
import es.consumo.gescom.modules.arbiter.repository.CollegiateTypeRepository;
import es.consumo.gescom.modules.arbiter.service.CollegiateTypeService;

@Service
public class CollegiateTypeServiceImpl implements CollegiateTypeService {

    @Autowired
    CollegiateTypeRepository collegiateTypeRepository;

    @Override
    public Page<CollegiateTypeEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<CollegiateTypeCriteria> wrapper) {
        return collegiateTypeRepository.findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }

}
