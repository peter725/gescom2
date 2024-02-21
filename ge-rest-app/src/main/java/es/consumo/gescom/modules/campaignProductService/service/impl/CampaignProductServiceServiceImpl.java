package es.consumo.gescom.modules.campaignProductService.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.campaignProductService.model.converter.CampaignProductServiceConverter;
import es.consumo.gescom.modules.campaignProductService.model.criteria.CampaignProductServiceCriteria;
import es.consumo.gescom.modules.campaignProductService.model.dto.CampaignProductServiceDTO;
import es.consumo.gescom.modules.campaignProductService.model.entity.CampaignProductServiceEntity;
import es.consumo.gescom.modules.campaignProductService.repository.CampaignProductServiceRepository;
import es.consumo.gescom.modules.campaignProductService.service.CampaignProductServiceService;
import es.consumo.gescom.modules.productServices.model.converter.ProductServiceConverter;
import es.consumo.gescom.modules.productServices.model.dto.ProductServiceDTO;
import es.consumo.gescom.modules.productServices.service.ProductServiceService;
import es.consumo.gescom.modules.protocol.model.converter.ProtocolConverter;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CampaignProductServiceServiceImpl extends EntityCrudService<CampaignProductServiceEntity, Long> implements CampaignProductServiceService {
    protected CampaignProductServiceServiceImpl(GESCOMRepository<CampaignProductServiceEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private CampaignProductServiceRepository campaignProductServiceRepository;

    @Autowired
    private CampaignProductServiceConverter campaignProductServiceConverter;

    @Autowired
    ProductServiceConverter productServiceConverter;

    @Autowired
    private ProductServiceService productServiceService;


    public Page<CampaignProductServiceEntity.SimpleProjection> findAllCampaignProductById(CriteriaWrapper<CampaignProductServiceCriteria> wrapper, Long id) {
        return ((CampaignProductServiceRepository) repository).findAllCampaignProductById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public List<CampaignProductServiceDTO> findCampaignProductServiceByCampaignId(Long id) {
        List<CampaignProductServiceEntity> campaignProductServiceEntities = campaignProductServiceRepository.findCampaignProductServiceByCampaignId(id);
        List<CampaignProductServiceDTO> campaignProductServiceDTOS = campaignProductServiceConverter.convertToModel(campaignProductServiceEntities);
        ProductServiceDTO productServiceDTO;
        for (CampaignProductServiceDTO campaignProductServiceDTO : campaignProductServiceDTOS) {
            if (campaignProductServiceDTO.getProductServiceId() == null) {
                productServiceDTO = productServiceConverter.convertToModel(productServiceService.findByCode(campaignProductServiceDTO.getCodeProductService()));
            }else {
                productServiceDTO = productServiceConverter.convertToModel(productServiceService.findProductServiceById(campaignProductServiceDTO.getProductServiceId()));
                campaignProductServiceDTO.setCodeProductService(productServiceDTO.getCode());
            }

            campaignProductServiceDTO.setProductName(productServiceDTO.getName());
        }
        return campaignProductServiceDTOS;
    }

    @Override
    public CampaignProductServiceEntity deleteByIdCPSE(Long id) {
        CampaignProductServiceEntity entity = findById(id).orElseThrow();
        entity.setState(2);

        return repository.save(entity);
    }
}
