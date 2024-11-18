package es.consumo.gescom.modules.productServices.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.campaign.model.dto.CampaignDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.productServices.model.converter.ProductServiceConverter;
import es.consumo.gescom.modules.productServices.model.criteria.ProductServiceCriteria;
import es.consumo.gescom.modules.productServices.model.dto.ProductServiceDTO;
import es.consumo.gescom.modules.productServices.model.entity.ProductServiceEntity;
import es.consumo.gescom.modules.productServices.repository.ProductServiceRepository;
import es.consumo.gescom.modules.productServices.service.ProductServiceService;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceServiceImpl extends EntityCrudService<ProductServiceEntity, Long> implements ProductServiceService {

    protected ProductServiceServiceImpl(GESCOMRepository<ProductServiceEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProductServiceRepository productServiceRepository;

    @Autowired
    private ProductServiceConverter productServiceConverter;


    public Page<ProductServiceEntity> findAllProductServiceById(CriteriaWrapper<ProductServiceCriteria> wrapper, Long id) {
        return ((ProductServiceRepository) repository).findAllProductServiceById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public ProductServiceDTO findByCode(String code) {
        return productServiceConverter.convertToModel(productServiceRepository.findProductServiceByCode(code));
    }

    @Override
    public ProductServiceDTO findProductServiceById(Long id) {
        return productServiceConverter.convertToModel(productServiceRepository.findProductServiceById(id));
    }



    @Override
    protected Page<ProductServiceEntity> findAllFromCriteria(FilterCriteria criteria) {
        ProductServiceCriteria productServiceCriteria = (ProductServiceCriteria) criteria;
        if (productServiceCriteria.getSearch() != null) {
            productServiceCriteria.setSearch(productServiceCriteria.getSearch().toUpperCase());
        }
        productServiceCriteria.setSort(productServiceCriteria.getSort());
        Page<ProductServiceEntity> productSimpleProjections = ((ProductServiceRepository) repository).findAllByCriteria(productServiceCriteria, criteria.toPageable());

        return productSimpleProjections;
    }

}
