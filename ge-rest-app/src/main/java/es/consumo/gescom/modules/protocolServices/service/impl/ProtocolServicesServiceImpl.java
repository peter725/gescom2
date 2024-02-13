package es.consumo.gescom.modules.protocolServices.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.protocolServices.model.criteria.ProtocolServicesCriteria;
import es.consumo.gescom.modules.protocolServices.model.entity.ProtocolServicesEntity;
import es.consumo.gescom.modules.protocolServices.repository.ProtocolServicesRepository;
import es.consumo.gescom.modules.protocolServices.service.ProtocolServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProtocolServicesServiceImpl extends EntityCrudService<ProtocolServicesEntity, Long> implements ProtocolServicesService {
    protected ProtocolServicesServiceImpl(GESCOMRepository<ProtocolServicesEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProtocolServicesRepository protocolServicesRepository;


    public Page<ProtocolServicesEntity.SimpleProjection> findAllProtocolServicesById(CriteriaWrapper<ProtocolServicesCriteria> wrapper, Long id) {
        return ((ProtocolServicesRepository) repository).findAllProtocolServicesById(wrapper.getCriteria().toPageable(), id);
    }
}
