package es.consumo.gescom.modules.protocolServices.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.protocolServices.model.criteria.ProductServiceCriteria;
import es.consumo.gescom.modules.protocolServices.model.entity.ProductServiceEntity;
import es.consumo.gescom.modules.protocolServices.repository.ProductServiceRepository;
import es.consumo.gescom.modules.protocolServices.service.ProductServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceServiceImpl extends EntityCrudService<ProductServiceEntity, Long> implements ProductServiceService {
    protected ProductServiceServiceImpl(GESCOMRepository<ProductServiceEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProductServiceRepository productServiceRepository;


    public Page<ProductServiceEntity.SimpleProjection> findAllProductServiceById(CriteriaWrapper<ProductServiceCriteria> wrapper, Long id) {
        return ((ProductServiceRepository) repository).findAllProductServiceById(wrapper.getCriteria().toPageable(), id);
    }
}
