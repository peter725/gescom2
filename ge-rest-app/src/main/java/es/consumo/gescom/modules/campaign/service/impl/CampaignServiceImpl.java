package es.consumo.gescom.modules.campaign.service.impl;

import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.ambit.model.converter.AmbitConverter;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.converter.AutonomousCommunityConverter;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.repository.AutonomousCommunityRepository;
import es.consumo.gescom.modules.autonomousCommunity.service.AutonomousCommunityService;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.converter.AutonomousCommunityParticipantsConverter;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.entity.AutonomousCommunityParticipantsEntity;
import es.consumo.gescom.modules.autonomousCommunityParticipants.repository.AutonomousCommunityParticipantsRepository;
import es.consumo.gescom.modules.autonomousCommunityParticipants.service.AutonomousCommunityParticipantsService;
import es.consumo.gescom.modules.autonomousCommunityProponent.model.entity.AutonomousCommunityProponentEntity;
import es.consumo.gescom.modules.autonomousCommunityProponent.repository.AutonomousCommunityProponentRepository;
import es.consumo.gescom.modules.autonomousCommunityProponent.service.AutonomousCommunityProponentService;
import es.consumo.gescom.modules.autonomousCommunitySpecialist.model.entity.AutonomousCommunitySpecialistEntity;
import es.consumo.gescom.modules.autonomousCommunitySpecialist.repository.AutonomousCommunitySpecialistRepository;
import es.consumo.gescom.modules.autonomousCommunitySpecialist.service.AutonomousCommunitySpecialistService;
import es.consumo.gescom.modules.campaign.model.converter.CampaignConverter;
import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.dto.*;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.repository.CampaignRepository;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import es.consumo.gescom.modules.campaignProductService.model.converter.CampaignProductServiceConverter;
import es.consumo.gescom.modules.campaignProductService.model.dto.CampaignProductServiceDTO;
import es.consumo.gescom.modules.campaignProductService.model.entity.CampaignProductServiceEntity;
import es.consumo.gescom.modules.campaignProductService.repository.CampaignProductServiceRepository;
import es.consumo.gescom.modules.campaignProductService.service.CampaignProductServiceService;
import es.consumo.gescom.modules.campaignType.model.converter.CampaingnTypeConverter;
import es.consumo.gescom.modules.campaignType.model.entity.CampaignTypeEntity;
import es.consumo.gescom.modules.ipr.repository.IprRepository;
import es.consumo.gescom.modules.ipr.service.IprService;
import es.consumo.gescom.modules.phase.model.converter.PhaseConverter;
import es.consumo.gescom.modules.phase.model.dto.PhaseDTO;
import es.consumo.gescom.modules.phase.model.entity.PhaseEntity;
import es.consumo.gescom.modules.phase.repository.PhaseRepository;
import es.consumo.gescom.modules.productServices.repository.ProductServiceRepository;
import es.consumo.gescom.modules.proponent.model.converter.ProponentConverter;
import es.consumo.gescom.modules.proponent.model.dto.ProponentDTO;
import es.consumo.gescom.modules.proponent.model.entity.ProponentEntity;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.repository.ProtocolRepository;
import es.consumo.gescom.modules.protocol.service.ProtocolService;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsDTO;
import es.consumo.gescom.modules.protocol_results.repository.ProtocolResultsRepository;
import es.consumo.gescom.modules.protocol_results.service.ProtocolResultsService;
import es.consumo.gescom.modules.role.model.entity.RoleHasModuleEntity;
import es.consumo.gescom.modules.specialist.model.converter.SpecialistConverter;
import es.consumo.gescom.modules.specialist.model.dto.SpecialistDTO;
import es.consumo.gescom.modules.specialist.model.entity.SpecialistEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class CampaignServiceImpl extends EntityCrudService<CampaignEntity, Long> implements CampaignService {
    protected CampaignServiceImpl(GESCOMRepository<CampaignEntity, Long> repository,
                                  AutonomousCommunityRepository autonomousCommunityRepository,
                                  AutonomousCommunityConverter autonomousCommunityConverter,
                                  CampaignRepository campaignRepository,
                                  PhaseRepository phaseRepository,
                                  CampaignConverter campaignConverter,
                                  AutonomousCommunityParticipantsRepository autonomousCommunityParticipantsRepository,
                                  ProponentConverter proponentConverter,
                                  SpecialistConverter specialistConverter,
                                  ProtocolService protocolService,
                                  AutonomousCommunityService autonomousCommunityService, CampaignProductServiceService campaignProductServiceService, IprService iprService,
                                  AutonomousCommunityParticipantsService autonomousCommunityParticipantsService,
                                  AutonomousCommunityProponentRepository autonomousCommunityProponentRepository, ProtocolService protocolService1,
                                  AutonomousCommunityProponentService autonomousCommunityProponentService, ProtocolResultsService protocolResultsService,
                                  AutonomousCommunitySpecialistRepository autonomousCommunitySpecialistRepository,
                                  AutonomousCommunitySpecialistService autonomousCommunitySpecialistService, CampaignProductServiceRepository campaignProductServiceRepository) {
        super(repository);
        this.autonomousCommunityRepository = autonomousCommunityRepository;
        this.autonomousCommunityConverter = autonomousCommunityConverter;
        this.campaignRepository = campaignRepository;
        this.campaingnConverter = campaignConverter;
        this.autonomousCommunityParticipantsRepository = autonomousCommunityParticipantsRepository;
        this.proponentConverter = proponentConverter;
        this.autonomousCommunityService = autonomousCommunityService;
        this.campaignProductServiceService = campaignProductServiceService;
        this.iprService = iprService;
        this.autonomousCommunityParticipantsService = autonomousCommunityParticipantsService;
        this.autonomousCommunityProponentRepository = autonomousCommunityProponentRepository;
        this.specialistConverter = specialistConverter;
        this.protocolService = protocolService1;
        this.autonomousCommunityProponentService = autonomousCommunityProponentService;
        this.protocolResultsService = protocolResultsService;
        this.autonomousCommunitySpecialistRepository = autonomousCommunitySpecialistRepository;
        this.autonomousCommunitySpecialistService = autonomousCommunitySpecialistService;
        this.campaignProductServiceRepository = campaignProductServiceRepository;
    }

    @Autowired
    private PhaseRepository phaseRepository;

    @Autowired
    private ProtocolRepository protocolRepository;

    @Autowired
    private ProductServiceRepository productServiceRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignConverter campaingnConverter;

    @Autowired
    private CampaingnTypeConverter campaingnTypeConverter;

    @Autowired
    private AmbitConverter ambitConverter;

    @Autowired
    private PhaseConverter phaseConverter;

    @Autowired
    private AutonomousCommunityConverter autonomousCommunityConverter;

    @Autowired
    private AutonomousCommunityParticipantsConverter autonomousCommunityParticipantsConverter;

    @Autowired
    private ProponentConverter proponentConverter;

    @Autowired
    private SpecialistConverter specialistConverter;

    @Autowired
    private CampaignProductServiceConverter campaignProductServiceConverter;

    @Autowired
    private IprRepository iprRepository;

    @Autowired
    private ProtocolResultsRepository protocolResultsRepository;

    private final AutonomousCommunityRepository autonomousCommunityRepository;

    private final AutonomousCommunityService autonomousCommunityService;

    private final CampaignProductServiceService campaignProductServiceService;

    @Autowired
    private final IprService iprService;

    private final AutonomousCommunityParticipantsRepository autonomousCommunityParticipantsRepository;

    private final AutonomousCommunityParticipantsService autonomousCommunityParticipantsService;

    private final AutonomousCommunityProponentRepository autonomousCommunityProponentRepository;

    private final ProtocolService protocolService;

    private final AutonomousCommunityProponentService autonomousCommunityProponentService;

    private final ProtocolResultsService protocolResultsService;

    private final AutonomousCommunitySpecialistRepository autonomousCommunitySpecialistRepository;

    private final AutonomousCommunitySpecialistService autonomousCommunitySpecialistService;

    private final CampaignProductServiceRepository campaignProductServiceRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CampaignEntity performCreate(CampaignEntity payload) {

        CampaignEntity campaignSave = super.performCreate(payload);


        return super.performCreate(payload);
    }




    @Override
    public CampaignDTO createCampaign(CampaignDTO campaignDTO) {
        CampaignEntity campaign = campaingnConverter.convertToEntity(campaignDTO);
        AutonomousCommunityEntity autonomousCommunityEntity = autonomousCommunityConverter.convertToEntity(campaignDTO.getAutonomousCommunityResponsible());
        campaign.setAutonomousCommunityResponsible(autonomousCommunityEntity);
        CampaignTypeEntity campaignType = campaingnTypeConverter.convertToEntity(campaignDTO.getCampaignType());
        campaign.setCampaignType(campaignType);
        AmbitEntity ambit = ambitConverter.convertToEntity(campaignDTO.getAmbit());
        campaign.setAmbit(ambit);

        if (campaignDTO.getId() == null) {
            PhaseEntity phaseEntityNew = new PhaseEntity();
            phaseEntityNew.setId(1L);
            campaign.setPhaseCampaign(phaseEntityNew);
        }

        CampaignEntity campaignSave = campaignRepository.save(campaign);

        List<AutonomousCommunityDTO> participantsList = campaignDTO.getParticipants();
        List<AutonomousCommunityEntity> autonomousCommunityEntities = autonomousCommunityConverter.convertToEntity(participantsList);
        autonomousCommunityEntities.forEach(participant -> {
            AutonomousCommunityParticipantsEntity autonomousCommunityParticipants = new AutonomousCommunityParticipantsEntity();
            autonomousCommunityParticipants.setCampaign(campaignSave);
            autonomousCommunityParticipants.setAutonomousCommunityEntity(participant);
            autonomousCommunityParticipants.setCreatedAt(LocalDateTime.now());
            autonomousCommunityParticipants.setUpdatedAt(LocalDateTime.now());
            autonomousCommunityParticipantsRepository.save(autonomousCommunityParticipants);
        });

        List<ProponentDTO> proponentDTOList = campaignDTO.getProponents();
        List<ProponentEntity> proponentEntityList = proponentConverter.convertToEntity(proponentDTOList);
        proponentEntityList.forEach(proponent -> {
            AutonomousCommunityProponentEntity autonomousCommunityProponentEntity = new AutonomousCommunityProponentEntity();
            autonomousCommunityProponentEntity.setCampaign(campaignSave);
            autonomousCommunityProponentEntity.setProponent(proponent);
            autonomousCommunityProponentEntity.setCreatedAt(LocalDateTime.now());
            autonomousCommunityProponentEntity.setUpdatedAt(LocalDateTime.now());
            autonomousCommunityProponentRepository.save(autonomousCommunityProponentEntity);
        });

        List<SpecialistDTO> specialistDTOS = campaignDTO.getSpecialists();
        List<SpecialistEntity> specialistEntityList = specialistConverter.convertToEntity(specialistDTOS);
        specialistEntityList.forEach(specialist -> {
            AutonomousCommunitySpecialistEntity autonomousCommunitySpecialistEntity = new AutonomousCommunitySpecialistEntity();
            autonomousCommunitySpecialistEntity.setCampaign(campaignSave);
            autonomousCommunitySpecialistEntity.setSpecialist(specialist);
            autonomousCommunitySpecialistEntity.setCreatedAt(LocalDateTime.now());
            autonomousCommunitySpecialistEntity.setUpdatedAt(LocalDateTime.now());
            autonomousCommunitySpecialistRepository.save(autonomousCommunitySpecialistEntity);
        });

        List<CampaignProductServiceDTO> productServiceDTOList = campaignDTO.getCampaignProductServiceDTOS();
        List<CampaignProductServiceEntity> productServiceEntities = campaignProductServiceConverter.convertToEntity(productServiceDTOList);
        productServiceEntities.forEach(productService -> {
            CampaignProductServiceEntity campaignProductServiceEntity = new CampaignProductServiceEntity();
            campaignProductServiceEntity.setCampaignId(campaignSave.getId());
            campaignProductServiceEntity.setCodeProductService(productService.getCodeProductService());
            campaignProductServiceEntity.setBkCpnops(productService.getBkCpnops());
            campaignProductServiceEntity.setCode(productService.getCode());
            campaignProductServiceEntity.setProductServiceId(productService.getProductServiceId());
            campaignProductServiceEntity.setCreatedAt(LocalDateTime.now());
            campaignProductServiceEntity.setUpdatedAt(LocalDateTime.now());
            campaignProductServiceRepository.save(campaignProductServiceEntity);
        });

        CampaignDTO respuesta = campaingnConverter.convertToModel(campaignSave);
        respuesta.setParticipants(participantsList);
        respuesta.setProponents(proponentDTOList);
        respuesta.setSpecialists(specialistDTOS);

        return respuesta;
    }

    @Override
    public CampaignDTO updateCampaign(Long idCampaing, CampaignDTO campaignDTO) {
        CampaignDTO campaign = this.findCampaignById(idCampaing);
        final List<Long> toDelete = new ArrayList<>();
        final List<Long> toDeleteProponent = new ArrayList<>();
        final List<Long> toDeleteSpecialist = new ArrayList<>();
        final List<RoleHasModuleEntity> toSave = new ArrayList<>();

        if (ObjectUtils.isEmpty(campaign.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (campaign.getId() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        List<AutonomousCommunityParticipantsEntity> acParticipantes = autonomousCommunityParticipantsRepository.findByIdCampaign(campaignDTO.getId());
        iterateAndDeletedACParticipants(acParticipantes, toDelete);

        if (!toDelete.isEmpty())
            autonomousCommunityParticipantsRepository.deleteAllById(toDelete);

        List<AutonomousCommunityProponentEntity> acProponentes = autonomousCommunityProponentRepository.findByIdCampaign(campaignDTO.getId());
        iterateAndDeletedACProponents(acProponentes, toDeleteProponent);

        if (!toDeleteProponent.isEmpty())
            autonomousCommunityProponentRepository.deleteAllById(toDeleteProponent);

        List<AutonomousCommunitySpecialistEntity> acSpecialists = autonomousCommunitySpecialistRepository.findByIdCampaign(campaignDTO.getId());
        iterateAndDeletedACSpecialist(acSpecialists, toDeleteSpecialist);

        if (!toDeleteSpecialist.isEmpty())
            autonomousCommunitySpecialistRepository.deleteAllById(toDeleteSpecialist);

        campaignDTO.setCreatedAt(campaign.getCreatedAt());
        campaignDTO.setCreatedBy(campaign.getCreatedBy());
        campaignDTO.setUpdatedAt(LocalDateTime.now());
        campaignDTO.setUpdatedBy(campaign.getUpdatedBy());
        campaignDTO.setPhaseCampaign(campaign.getPhaseCampaign());

        return createCampaign(campaignDTO);

    }


    private void iterateAndDeletedACParticipants(List<AutonomousCommunityParticipantsEntity> autonomousCommunityParticipantsDTOS, List<Long> toDelete) {
        for (AutonomousCommunityParticipantsEntity acParticipantsDTO : autonomousCommunityParticipantsDTOS) {
            if (acParticipantsDTO.getId() != 0) {
                if (Objects.nonNull(toDelete)) {
                    toDelete.add(acParticipantsDTO.getId());
                }
            }
        }
    }

    private void iterateAndDeletedACProponents(List<AutonomousCommunityProponentEntity> communityProponentEntityList, List<Long> toDeleteProponents) {
        for (AutonomousCommunityProponentEntity proponentEntity : communityProponentEntityList) {
            if (proponentEntity.getId() != 0) {
                if (Objects.nonNull(toDeleteProponents)) {
                    toDeleteProponents.add(proponentEntity.getId());
                }
            }
        }
    }

    private void iterateAndDeletedACSpecialist(List<AutonomousCommunitySpecialistEntity> autonomousCommunitySpecialistEntityList, List<Long> toDeleteSpecialist) {
        for (AutonomousCommunitySpecialistEntity autonomousCommunitySpecialistEntity : autonomousCommunitySpecialistEntityList) {
            if (autonomousCommunitySpecialistEntity.getId() != 0) {
                if (Objects.nonNull(toDeleteSpecialist)) {
                    toDeleteSpecialist.add(autonomousCommunitySpecialistEntity.getId());
                }
            }
        }
    }

    public CampaignDTO findCampaignById(Long idCampaign) {
        CampaignEntity campaign = campaignRepository.findById(idCampaign)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró registro con ID: " + idCampaign));
        List<ProtocolDTO> protocolDTO = protocolService.findProtocolByCampaignId(idCampaign);
        List<ProtocolResultsDTO>  protocolResultsDTOS = protocolResultsService.findProtocolResultsByCampaignId(idCampaign);
        protocolResultsDTOS.forEach(protocolResultsDTO -> {
            protocolDTO.forEach(protocolDTO1 -> {
                if(protocolDTO1.getId().equals(protocolResultsDTO.getProtocolDTO().getId())){
                    protocolResultsDTO.getProtocolDTO().setQuestion(protocolDTO1.getQuestion());
                }
                if (protocolDTO1.getCode() != null) {
                    protocolDTO1.setIprDTOS(iprService.findAllIprByCampaignIdAndProtocolCode(idCampaign, protocolDTO1.getCode()));
                }else {
                    protocolDTO1.setIprDTOS(iprService.findAllIprByCampaignIdAndProtocolId(idCampaign, protocolDTO1.getId()));
                }

            });
        });

        if (campaign != null) {
            CampaignDTO campaignDTO = campaingnConverter.convertToModel(campaign);
            campaignDTO.setParticipants(autonomousCommunityParticipantsService.findByIdCampaign(idCampaign));
            campaignDTO.setProponents(autonomousCommunityProponentService.finByIdCampaign(idCampaign));
            campaignDTO.setSpecialists(autonomousCommunitySpecialistService.finByIdCampaign(idCampaign));
            campaignDTO.setCampaignProductServiceDTOS(campaignProductServiceService.findCampaignProductServiceByCampaignId(idCampaign));
            campaignDTO.setProtocols(protocolDTO);
            campaignDTO.setProtocolResultsDTOS(protocolResultsDTOS);

            return campaignDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign not found");

        }

    }

    @Override
    protected Page<CampaignEntity> findAllFromCriteria(FilterCriteria criteria) {

        CampaignCriteria campaignCriteria = (CampaignCriteria) criteria;
        if (campaignCriteria.getSearch() != null) {
            campaignCriteria.setSearch(criteria.getSearch().toUpperCase());
        }
        if (campaignCriteria.getAmbit() != null) {
            campaignCriteria.setAmbit(campaignCriteria.getAmbit().toUpperCase());
        }
        if (campaignCriteria.getCampaignType() != null) {
            campaignCriteria.setCampaignType(campaignCriteria.getCampaignType().toUpperCase());
        }
        if (campaignCriteria.getPhaseCampaign() != null) {
            campaignCriteria.setPhaseCampaign(campaignCriteria.getPhaseCampaign().toUpperCase());
        }
        if (campaignCriteria.getNameCampaign() != null) {
            campaignCriteria.setNameCampaign(campaignCriteria.getNameCampaign().toUpperCase());
        }
//        if (campaignCriteria.getYear() != null) {
//            campaignCriteria.setYear(campaignCriteria.getYear().replaceAll("%", ""));
//            campaignCriteria.setYearLong(Long.parseLong(campaignCriteria.getYear().replaceAll("%", "")));
//        }
        campaignCriteria.setSort(new String[]{"id;desc"});
        Page<CampaignEntity> campaignEntities = campaignRepository.findAllByCriteria(campaignCriteria, criteria.toPageable());

        return campaignEntities;
    }

    @Override
    public Page<CampaignDTO> performFindAllCampaing(CriteriaWrapper<?> wrapper) {
        Page<CampaignEntity> listCampaign = findAllFromCriteria(wrapper.getCriteria());
        /*Page<CampaignEntity> listCampaign = repository.findAll(wrapper.getCriteria().toPageable());*/
        List<CampaignDTO> listCampaingDTO = new ArrayList<>();
        for (CampaignEntity campaign : listCampaign) {
            CampaignDTO campaignDTO = campaingnConverter.convertToModel(campaign);
            campaignDTO.setParticipants(autonomousCommunityParticipantsService.findByIdCampaign(campaign.getId()));
            campaignDTO.setProponents(autonomousCommunityProponentService.finByIdCampaign(campaign.getId()));
            campaignDTO.setSpecialists(autonomousCommunitySpecialistService.finByIdCampaign(campaign.getId()));
            listCampaingDTO.add(campaignDTO);
        }
        if(listCampaingDTO != null){
            return new PageImpl<>(listCampaingDTO, PageRequest.of(wrapper.getCriteria().toPageable().getPageNumber(), wrapper.getCriteria().toPageable().getPageSize()), listCampaign.getTotalElements());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign not found");

        }
        
    }

    @Override
    protected Page<?> findAllFromPageable(Pageable pageable) {
        return super.findAllFromPageable(pageable);
    }

    @Override
    public CampaignEntity switchStatus(ChangeStatusDTO changeStatusDTO, Long id) {
        CampaignEntity entity = findById(id).orElseThrow();
        entity.setState(changeStatusDTO.getStatus());

        return repository.save(entity);
    }

    @Override
    public CampaignEntity switchPhase(PhaseDTO changeStatus, Long id) {
        CampaignEntity entity = findById(id).orElseThrow();
        PhaseEntity phase = phaseConverter.convertToEntity(changeStatus);
        entity.setPhaseCampaign(phase);

        return repository.save(entity);
    }

   /* @Override
    public ResultsResponseDTO getResults(SearchDTO searchDTO) {
        ResultsResponseDTO resultsResponseDTO = new ResultsResponseDTO();
        List<QuestionsResponseDTO> questionsResponseDTOS = new ArrayList<>();
        List<IprResponseDTO> iprResponseDTOS = new ArrayList<>();
        List<ProtocolResultsResponseDTO> protocolResultsResponseDTOS = new ArrayList<>();
        ProductServiceEntity productServiceEntity = new ProductServiceEntity();
        ProtocolEntity protocolEntity = new ProtocolEntity();

        if (searchDTO.getProtocolCode() != null) {
            iprResponseDTOS = iprRepository.findAllIprByCampaignIdAndProtocolCode(searchDTO.getCampaignId(), searchDTO.getProtocolCode(), searchDTO.getIprCode());
            protocolResultsResponseDTOS = protocolResultsRepository.findProtocolResultsByCampaignIdAndProtocolCode(searchDTO.getCampaignId(), searchDTO.getProtocolCode(), searchDTO.getProductServiceCode());
        }else {
            iprResponseDTOS = iprRepository.findAllIprByCampaignIdAndProtocolId(searchDTO.getCampaignId(), searchDTO.getProtocolId());
            protocolResultsResponseDTOS = protocolResultsRepository.findProtocolResultsByCampaignIdAndProtocolId(searchDTO.getCampaignId(), searchDTO.getProtocolId(), searchDTO.getProductServiceId());

        }

        CampaignEntity campaignEntity = campaignRepository.findById(searchDTO.getCampaignId()).orElseThrow();
        if (searchDTO.getProtocolCode() != null) {
            protocolEntity = protocolRepository.findProtocolNameByCode(searchDTO.getProtocolCode());
        }else {
            protocolEntity = protocolRepository.findById(searchDTO.getProtocolId()).orElseThrow();
        }

        if (searchDTO.getProductServiceCode() != null) {
            productServiceEntity = productServiceRepository.findProductServiceByCode(searchDTO.getProductServiceCode());
        }else {
            productServiceEntity = productServiceRepository.findProductServiceById(searchDTO.getProductServiceId());
        }

        resultsResponseDTO.setCampaignName(campaignEntity.getNameCampaign());
        resultsResponseDTO.setProtocolName(protocolEntity.getName());
        resultsResponseDTO.setProductName(productServiceEntity.getCode().concat(" - ").concat(productServiceEntity.getName()));

        for (IprResponseDTO iprResponseDTO : iprResponseDTOS ){

            QuestionsResponseDTO questionsResponseDTO = new QuestionsResponseDTO();
            String question = iprResponseDTO.getQuestion();
            String questionText = question != null ? question : "null"; // Si question es null, usa "null", de lo contrario, usa el valor de question
            questionsResponseDTO.setQuestion(questionText);
            questionsResponseDTO.setOrderQuestion(iprResponseDTO.getOrderQuestion());

            if (Objects.equals(iprResponseDTO.getFormula(), "DC0")) {
                for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                    if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC0")) {
                        questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                        if (iprResponseDTO.getPercentageRespectTo() != null)
                            questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                    }
                }
                questionsResponseDTOS.add(questionsResponseDTO);
                continue;
            }
            if (Objects.equals(iprResponseDTO.getFormula(), "DC8")){
                for ( ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                    if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC8")){
                        questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                        if (iprResponseDTO.getPercentageRespectTo() != null)
                            questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                    }
                }
                questionsResponseDTOS.add(questionsResponseDTO);
                continue;
            }
            if (Objects.equals(iprResponseDTO.getFormula(), "DC9")) {
                for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                    if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC9")) {
                        questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                        if (iprResponseDTO.getPercentageRespectTo() != null)
                            questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                    }
                }
                questionsResponseDTOS.add(questionsResponseDTO);
                continue;
            }
            if (Objects.equals(iprResponseDTO.getFormula(), "DC10")) {
                for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                    if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC10")) {
                        questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                        if (iprResponseDTO.getPercentageRespectTo() != null)
                            questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                    }
                }
                questionsResponseDTOS.add(questionsResponseDTO);
                continue;

            }
            if (Objects.equals(iprResponseDTO.getFormula(), "DC11")) {
                for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                    if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC11")) {
                        questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                        if (iprResponseDTO.getPercentageRespectTo() != null)
                            questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                    }
                }
                questionsResponseDTOS.add(questionsResponseDTO);
                continue;
            }

            if ( iprResponseDTO.getFormula() == null) {
                questionsResponseDTOS.add(questionsResponseDTO);
                continue;
            }else {

                for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {

                    if (iprResponseDTO.getPercentageRespectTo() != null)
                        questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());

                    if (iprResponseDTO.getFormula() != null && !protocolResultsResponseDTO.getCodeQuestion().startsWith("DC")){
                        List<String> componentes = descomponerFormula(iprResponseDTO.getFormula());
                        interpretarYProcesarComponentes(componentes, protocolResultsResponseDTO, questionsResponseDTO);
                    }else{
                        continue;
                    }

                }
            }


            questionsResponseDTOS.add(questionsResponseDTO);
        }

        setPercentage(questionsResponseDTOS);

        resultsResponseDTO.setQuestionsResponseDTOS(questionsResponseDTOS);

        return resultsResponseDTO;
    }

    private List<String> descomponerFormula(String formula) {
        List<String> componentes = new ArrayList<>();
        // La expresión regular incluye operadores de suma y resta
        Pattern pattern = Pattern.compile("([NSNP]+\\d+)|([+-])");
        Matcher matcher = pattern.matcher(formula);

        while (matcher.find()) {
            componentes.add(matcher.group());
        }

        return componentes;
    }

    private void interpretarYProcesarComponentes(List<String> componentes, ProtocolResultsResponseDTO protocolResultsResponseDTO, QuestionsResponseDTO questionsResponseDTO) {
        for (String componente : componentes) {
            if (componentes.size() == 1 && !componentes.get(0).matches("[+-]")) {
                // Solo hay un componente y no es un operador matemático
                procesarComponenteIndividual(componentes.get(0), protocolResultsResponseDTO, questionsResponseDTO);
            }else {

                String tipo = componente.replaceAll("\\d+", "");

                if (tipo.equals("+") || tipo.equals("-")) {
                    continue;
                }else {
                    int orden = Integer.parseInt(componente.replaceAll("\\D+", ""));

                    switch (tipo) {
                        case "N":
                            procesarTipoN(orden, protocolResultsResponseDTO, questionsResponseDTO);
                            break;
                        case "S":
                            procesarTipoS(orden,  protocolResultsResponseDTO, questionsResponseDTO);
                            break;
                        case "NP":
                            procesarTipoNP(orden, protocolResultsResponseDTO, questionsResponseDTO);
                            break;
                    }
                }
            }
        }
    }

    private void procesarComponenteIndividual(String componente, ProtocolResultsResponseDTO protocolResultsResponseDTO, QuestionsResponseDTO questionsResponseDTO) {
        String tipo = componente.replaceAll("\\d+", "");
        int orden = Integer.parseInt(componente.replaceAll("\\D+", ""));

        switch (tipo) {
            case "N":
                procesarTipoN(orden, protocolResultsResponseDTO, questionsResponseDTO);
                break;
            case "S":
                procesarTipoS(orden, protocolResultsResponseDTO, questionsResponseDTO);
                break;
            case "NP":
                procesarTipoNP(orden, protocolResultsResponseDTO, questionsResponseDTO);
                break;
        }


    }

    private void procesarTipoN(int orden, ProtocolResultsResponseDTO protocolResultsResponseDTO, QuestionsResponseDTO questionsResponseDTO) {
        if (orden == Integer.parseInt(protocolResultsResponseDTO.getCodeQuestion())){
            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRen());
        }
    }

    private void procesarTipoS(int orden, ProtocolResultsResponseDTO protocolResultsResponseDTO, QuestionsResponseDTO questionsResponseDTO) {
        if (orden == Integer.parseInt(protocolResultsResponseDTO.getCodeQuestion())){
            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
        }
    }

    private void procesarTipoNP(int orden, ProtocolResultsResponseDTO protocolResultsResponseDTO, QuestionsResponseDTO questionsResponseDTO) {
        if (orden == Integer.parseInt(protocolResultsResponseDTO.getCodeQuestion())){
            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRep());
        }
    }

    private void setPercentage(List<QuestionsResponseDTO> questionsResponseDTOS) {

        for (QuestionsResponseDTO questionsResponseDTO : questionsResponseDTOS) {
            if (questionsResponseDTO.getPercentageRespectTo() != null) {

                for (QuestionsResponseDTO item : questionsResponseDTOS) {
                    if (questionsResponseDTO.getPercentageRespectTo().equals(item.getOrderQuestion())) {
                        Float value = questionsResponseDTO.getTotal().floatValue() / item.getTotal() * 100;
                        BigDecimal bd = new BigDecimal(value);
                        bd = bd.setScale(2, RoundingMode.HALF_UP); // Redondeo a 2 decimales
                        questionsResponseDTO.setPercentage(bd.floatValue());
                    }
                }
            }
        }

    }*/


}
