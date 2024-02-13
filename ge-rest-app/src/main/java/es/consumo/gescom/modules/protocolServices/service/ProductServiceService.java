package es.consumo.gescom.modules.protocolServices.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.protocolServices.model.criteria.ProductServiceCriteria;
import es.consumo.gescom.modules.protocolServices.model.entity.ProductServiceEntity;
import org.springframework.data.domain.Page;

public interface ProductServiceService extends CrudService<ProductServiceEntity, Long>{

    Page<ProductServiceEntity.SimpleProjection> findAllProductServiceById(CriteriaWrapper<ProductServiceCriteria> wrapper, Long id);
    
}
