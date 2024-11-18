package es.consumo.gescom.modules.protocol_results.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.autonomousCommunityCountry.service.AutonomousCommunityCountryService;
import es.consumo.gescom.modules.productServices.service.ProductServiceService;
import es.consumo.gescom.modules.protocol.service.ProtocolService;
import es.consumo.gescom.modules.protocol_results.model.converter.ProtocolResultsConverter;
import es.consumo.gescom.modules.protocol_results.model.criteria.ProtocolResultsCriteria;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsDTO;
import es.consumo.gescom.modules.protocol_results.model.entity.ProtocolResultsEntity;
import es.consumo.gescom.modules.protocol_results.repository.ProtocolResultsRepository;
import es.consumo.gescom.modules.protocol_results.service.ProtocolResultsService;
import es.consumo.gescom.modules.totalProtocolResults.model.dto.TotalProtocolResultsDTO;
import es.consumo.gescom.modules.totalProtocolResults.model.entity.TotalProtocolResultsEntity;
import es.consumo.gescom.modules.totalProtocolResults.repository.TotalProtocolResultsRepository;
import es.consumo.gescom.modules.totalProtocolResults.service.TotalProtocolResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class ProtocolResultsServiceImpl extends EntityCrudService<ProtocolResultsEntity, Long> implements ProtocolResultsService {
    protected ProtocolResultsServiceImpl(GESCOMRepository<ProtocolResultsEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProtocolResultsRepository protocolResultsRepository;

    @Autowired
    private TotalProtocolResultsRepository totalProtocolResultsRepository;

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
    public ProtocolResultsEntity saveProtocolResults(ProtocolResultsDTO protocolResults) {
        ProtocolResultsEntity protocolResultsEntity = protocolResultsConverter.convertToEntity(protocolResults);
        protocolResultsEntity.setAutonomousCommunityCountryId(protocolResults.getAutonomousCommunityCountryDTO().getId());
        ProtocolResultsEntity protocolResultsEntitySave = protocolResultsRepository.save(protocolResultsEntity);

        List<TotalProtocolResultsDTO> totalProtocolResultsDTOS = protocolResults.getTotalProtocolResultsDTOS();
        totalProtocolResultsDTOS.forEach(totalProtocolResults -> {
            TotalProtocolResultsEntity totalProtocolResultsEntity = new TotalProtocolResultsEntity();
            totalProtocolResultsEntity.setCcaaRen(totalProtocolResults.getCcaaRen());
            totalProtocolResultsEntity.setCcaaRep(totalProtocolResults.getCcaaRep());
            totalProtocolResultsEntity.setCcaaRes(totalProtocolResults.getCcaaRes());
            totalProtocolResultsEntity.setCode(totalProtocolResults.getCode());
            totalProtocolResultsEntity.setProtocolResultsCode(totalProtocolResults.getProtocolResultsCode());
            totalProtocolResultsEntity.setCodeQuestion(totalProtocolResults.getCodeQuestion());
            totalProtocolResultsEntity.setProtocolResultsId(protocolResultsEntitySave.getId());

            totalProtocolResultsRepository.save(totalProtocolResultsEntity);
        });

        return protocolResultsEntitySave;
    }

    public List<ProtocolResultsDTO> findProtocolResultsByCampaignId(Long campaignId) {
        List<ProtocolResultsEntity> protocolResultsEntities = protocolResultsRepository.findAllByCampaignId(campaignId);
        List<ProtocolResultsDTO> protocolResultsDTOS = protocolResultsConverter.convertToModel(protocolResultsEntities);
        for (ProtocolResultsDTO protocolResultsDTO : protocolResultsDTOS){

            if (protocolResultsDTO.getAutonomousCommunityCountryCode() != null){
                protocolResultsDTO.setAutonomousCommunityCountryDTO(autonomousCommunityCountryService.findByCode(protocolResultsDTO.getAutonomousCommunityCountryCode()));
            }else{
                protocolResultsDTO.setAutonomousCommunityCountryDTO(autonomousCommunityCountryService.findCCAAById(protocolResultsDTO.getAutonomousCommunityCountryId()));
            }


            if (protocolResultsDTO.getProductServiceCode() != null){
                protocolResultsDTO.setProductServiceDTO(productServiceService.findByCode(protocolResultsDTO.getProductServiceCode()));
            }else{
                protocolResultsDTO.setProductServiceDTO(productServiceService.findProductServiceById(protocolResultsDTO.getProductServiceId()));
            }

            protocolResultsDTO.setTotalProtocolResultsDTOS(totalProtocolResultsService.findByProtocolResultsId(protocolResultsDTO));

            if (protocolResultsDTO.getProtocolCode() != null){
                protocolResultsDTO.setProtocolDTO(protocolService.findProtocolByCode(protocolResultsDTO.getProtocolCode()));
            }else{
                protocolResultsDTO.setProtocolDTO(protocolService.findProtocolDTOById(protocolResultsDTO.getProtocolId()));
            }


        }

        return protocolResultsDTOS;
    }

    @Override
    public ProtocolResultsDTO updateProtocolResults(Long id, ProtocolResultsDTO protocolResultsDTO) {
        ProtocolResultsEntity protocolResultsEntity = protocolResultsConverter.convertToEntity(protocolResultsDTO);
        ProtocolResultsEntity oldEntity = protocolResultsRepository.findById(id).get();
        protocolResultsEntity.setCreatedAt(oldEntity.getCreatedAt());
        protocolResultsEntity.setUpdatedAt(oldEntity.getUpdatedAt());
        protocolResultsEntity.setCreatedBy(oldEntity.getCreatedBy());
        protocolResultsEntity.setUpdatedBy(oldEntity.getUpdatedBy());
        protocolResultsEntity.setState(oldEntity.getState());
        final List<Long> toDelete = new ArrayList<>();

        if (protocolResultsEntity.getId() == null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ID del es requerido para la actualización.");
        }

        ProtocolResultsEntity entitySave = protocolResultsRepository.save(protocolResultsEntity);
        if (protocolResultsDTO.getCode() != null){
            List<TotalProtocolResultsEntity> totalProtocolResultsEntities = totalProtocolResultsRepository.findAllByProtocolResultsCode(protocolResultsDTO.getCode());
            iterateForDeletedTotalProtocolResults(totalProtocolResultsEntities, toDelete);
        }else{
            List<TotalProtocolResultsEntity> totalProtocolResultsEntities = totalProtocolResultsRepository.findAllByProtocolResultsId(protocolResultsDTO.getId());
            iterateForDeletedTotalProtocolResults(totalProtocolResultsEntities, toDelete);
        }

        if (!toDelete.isEmpty()){
            totalProtocolResultsRepository.deleteAllById(toDelete);
        }

        List<TotalProtocolResultsDTO> totalProtocolResultsDTOS = protocolResultsDTO.getTotalProtocolResultsDTOS();
        totalProtocolResultsDTOS.forEach(item ->{
            TotalProtocolResultsEntity totalProtocolResultsEntity = new TotalProtocolResultsEntity();
            totalProtocolResultsEntity.setCcaaRen(item.getCcaaRen());
            totalProtocolResultsEntity.setCcaaRep(item.getCcaaRep());
            totalProtocolResultsEntity.setCcaaRes(item.getCcaaRes());
            totalProtocolResultsEntity.setCode(item.getCode());
            totalProtocolResultsEntity.setProtocolResultsCode(item.getProtocolResultsCode());
            totalProtocolResultsEntity.setCodeQuestion(item.getCodeQuestion());
            totalProtocolResultsEntity.setProtocolResultsId(item.getProtocolResultsId());
            totalProtocolResultsEntity.setUpdatedAt(LocalDateTime.now());

            totalProtocolResultsRepository.save(totalProtocolResultsEntity);
        });
        return protocolResultsConverter.convertToModel(entitySave);
    }

    private void iterateForDeletedTotalProtocolResults(List<TotalProtocolResultsEntity> totalProtocolResultsEntities, List<Long> toDelete){
        for (TotalProtocolResultsEntity totalProtocolResultsEntity : totalProtocolResultsEntities){
            if (totalProtocolResultsEntity.getId() != (0)){
                if (Objects.nonNull(toDelete)){
                    toDelete.add(totalProtocolResultsEntity.getId());
                }
            }
        }
    }

}
