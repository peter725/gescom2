package es.consumo.gescom.modules.protocol_results.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.autonomousCommunityCountry.service.AutonomousCommunityCountryService;
import es.consumo.gescom.modules.productServices.model.entity.ProductServiceEntity;
import es.consumo.gescom.modules.productServices.service.ProductServiceService;
import es.consumo.gescom.modules.protocol.service.ProtocolService;
import es.consumo.gescom.modules.protocol_results.model.converter.ProtocolResultsConverter;
import es.consumo.gescom.modules.protocol_results.model.criteria.ProtocolResultsCriteria;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsDTO;
import es.consumo.gescom.modules.protocol_results.model.entity.ProtocolResultsEntity;
import es.consumo.gescom.modules.protocol_results.repository.ProtocolResultsRepository;
import es.consumo.gescom.modules.protocol_results.service.ProtocolResultsService;
import es.consumo.gescom.modules.totalProtocolResults.service.TotalProtocolResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProtocolResultsServiceImpl extends EntityCrudService<ProtocolResultsEntity, Long> implements ProtocolResultsService {
    protected ProtocolResultsServiceImpl(GESCOMRepository<ProtocolResultsEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProtocolResultsRepository protocolResultsRepository;

    @Autowired
    private TotalProtocolResultsService totalProtocolResultsService;

    @Autowired
    private ProtocolResultsConverter protocolResultsConverter;

    @Autowired
    private ProtocolService protocolService;

    @Autowired
    private ProductServiceService productServiceService;

    @Autowired
    private AutonomousCommunityCountryService autonomousCommunityCountryService;

    @Override
    public Page<ProtocolResultsEntity.SimpleProjection> findAllSumProtocolById(CriteriaWrapper<ProtocolResultsCriteria> wrapper, Long id) {
        return ((ProtocolResultsRepository) repository).findAllSumProtocolById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public List<ProtocolResultsDTO> findProtocolResultsByCampaignId(Long campaignId) {
        List<ProtocolResultsEntity> protocolResultsEntities = protocolResultsRepository.findAllByCampaignId(campaignId);
        List<ProtocolResultsDTO> protocolResultsDTOS = protocolResultsConverter.convertToModel(protocolResultsEntities);
        for (ProtocolResultsDTO protocolResultsDTO : protocolResultsDTOS){

            if (protocolResultsDTO.getAutonomousCommunityCountryCode() != null){
                protocolResultsDTO.setAutonomousCommunityCountryName(autonomousCommunityCountryService.findByCode(protocolResultsDTO.getAutonomousCommunityCountryCode()).getName());
            }else{
                protocolResultsDTO.setAutonomousCommunityCountryName(autonomousCommunityCountryService.findCCAAById(protocolResultsDTO.getAutonomousCommunityCountryId()).getName());
            }


            if (protocolResultsDTO.getProductServiceCode() != null){
                protocolResultsDTO.setProductServiceName(productServiceService.findByCode(protocolResultsDTO.getProductServiceCode()).getName());
            }else{
                protocolResultsDTO.setProductServiceName(productServiceService.findProductServiceById(protocolResultsDTO.getProductServiceId()).getName());
            }

            protocolResultsDTO.setTotalProtocolResultsDTOS(totalProtocolResultsService.findByProtocolResultsId(protocolResultsDTO));

            if (protocolResultsDTO.getProtocolCode() != null){
                protocolResultsDTO.setProtocolName(protocolService.findProtocolNameByCode(protocolResultsDTO.getProtocolCode()));
            }else{
                protocolResultsDTO.setProtocolName(protocolService.findProtocolNameById(protocolResultsDTO.getProtocolId()));
            }


        }

        return protocolResultsDTOS;
    }


}
