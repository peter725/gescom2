package es.consumo.gescom.modules.protocolServices.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.protocolServices.model.criteria.ProtocolServicesCriteria;
import es.consumo.gescom.modules.protocolServices.model.entity.ProtocolServicesEntity;
import org.springframework.data.domain.Page;

public interface ProtocolServicesService extends CrudService<ProtocolServicesEntity, Long>{

    Page<ProtocolServicesEntity.SimpleProjection> findAllProtocolServicesById(CriteriaWrapper<ProtocolServicesCriteria> wrapper, Long id);
    
}
