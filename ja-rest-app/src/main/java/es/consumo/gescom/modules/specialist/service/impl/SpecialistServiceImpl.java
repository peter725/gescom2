package es.consumo.gescom.modules.specialist.service.impl;

import es.consumo.gescom.modules.specialist.model.criteria.SpecialistCriteria;
import es.consumo.gescom.modules.specialist.model.entity.SpecialistEntity;
import es.consumo.gescom.modules.specialist.repository.SpecialistRepository;
import es.consumo.gescom.modules.specialist.service.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class SpecialistServiceImpl extends EntityCrudService<SpecialistEntity, Long> implements SpecialistService {
    protected SpecialistServiceImpl(JJAARepository<SpecialistEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private SpecialistRepository specialistRepository;

    @Override
    public Page<SpecialistEntity.SimpleProjection> findAllSpecialistById(CriteriaWrapper<SpecialistCriteria> wrapper, Long id) {
        return ((SpecialistRepository) repository).findAllSpecialistById(wrapper.getCriteria().toPageable(), id);
    }
}
