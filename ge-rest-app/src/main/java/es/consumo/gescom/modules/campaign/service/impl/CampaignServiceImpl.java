package es.consumo.gescom.modules.campaign.service.impl;

import es.consumo.gescom.modules.autonomousCommunity.model.converter.AutonomousCommunityConverter;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.repository.AutonomousCommunityRepository;
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
import es.consumo.gescom.modules.campaign.model.dto.CampaignDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.repository.CampaignRepository;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import es.consumo.gescom.modules.phase.model.entity.PhaseEntity;
import es.consumo.gescom.modules.proponent.model.converter.ProponentConverter;
import es.consumo.gescom.modules.proponent.model.dto.ProponentDTO;
import es.consumo.gescom.modules.proponent.model.entity.ProponentEntity;
import es.consumo.gescom.modules.specialist.model.converter.SpecialistConverter;
import es.consumo.gescom.modules.specialist.model.dto.SpecialistDTO;
import es.consumo.gescom.modules.specialist.model.entity.SpecialistEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class CampaignServiceImpl extends EntityCrudService<CampaignEntity, Long> implements CampaignService {
    protected CampaignServiceImpl(GESCOMRepository<CampaignEntity, Long> repository,
                                  AutonomousCommunityRepository autonomousCommunityRepository,
                                  AutonomousCommunityConverter autonomousCommunityConverter,
                                  CampaignRepository campaignRepository,
                                  CampaignConverter campaignConverter,
                                  AutonomousCommunityParticipantsRepository autonomousCommunityParticipantsRepository,
                                  ProponentConverter proponentConverter,
                                  SpecialistConverter specialistConverter,
                                  AutonomousCommunityParticipantsService autonomousCommunityParticipantsService, AutonomousCommunityProponentRepository autonomousCommunityProponentRepository, AutonomousCommunityProponentService autonomousCommunityProponentService, AutonomousCommunitySpecialistRepository autonomousCommunitySpecialistRepository, AutonomousCommunitySpecialistService autonomousCommunitySpecialistService) {
        super(repository);
        this.autonomousCommunityRepository = autonomousCommunityRepository;
        this.autonomousCommunityConverter = autonomousCommunityConverter;
        this.campaignRepository = campaignRepository;
        this.campaingnConverter = campaignConverter;
        this.autonomousCommunityParticipantsRepository = autonomousCommunityParticipantsRepository;
        this.proponentConverter = proponentConverter;
        this.autonomousCommunityParticipantsService = autonomousCommunityParticipantsService;
        this.autonomousCommunityProponentRepository = autonomousCommunityProponentRepository;
        this.specialistConverter = specialistConverter;
        this.autonomousCommunityProponentService = autonomousCommunityProponentService;
        this.autonomousCommunitySpecialistRepository = autonomousCommunitySpecialistRepository;
        this.autonomousCommunitySpecialistService = autonomousCommunitySpecialistService;
    }

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignConverter campaingnConverter;

    @Autowired
    private AutonomousCommunityConverter autonomousCommunityConverter;

    @Autowired
    private ProponentConverter proponentConverter;

    @Autowired
    private SpecialistConverter specialistConverter;


    private final AutonomousCommunityRepository autonomousCommunityRepository;

    private final AutonomousCommunityParticipantsRepository autonomousCommunityParticipantsRepository;

    private final AutonomousCommunityParticipantsService autonomousCommunityParticipantsService;

    private final AutonomousCommunityProponentRepository autonomousCommunityProponentRepository;

    private final AutonomousCommunityProponentService autonomousCommunityProponentService;


    private final AutonomousCommunitySpecialistRepository autonomousCommunitySpecialistRepository;

    private final AutonomousCommunitySpecialistService autonomousCommunitySpecialistService;




    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CampaignEntity performCreate(CampaignEntity payload) {

        CampaignEntity campaignSave = super.performCreate(payload);


        return super.performCreate(payload);
    }

    @Override
    public CampaignEntity createCampaign(CampaignDTO campaignDTO) {
        CampaignEntity campaign = campaingnConverter.convertToEntity(campaignDTO);
        campaign.setAutonomousCommunityResponsible(campaignDTO.getAutonomousCommunityResponsible());
        campaign.setCampaignType(campaignDTO.getCampaignType());
        campaign.setAmbit(campaignDTO.getAmbit());

        if(campaignDTO.getId() == null){
            PhaseEntity phaseEntityNew = new PhaseEntity();
            phaseEntityNew.setId(1L);
            campaign.setPhaseCampaign(phaseEntityNew);
        }else{
            campaign.setPhaseCampaign(campaignDTO.getPhaseCampaign());
        }

        CampaignEntity campaignSave = campaignRepository.save(campaign);

        List<AutonomousCommunityDTO> participantsList = campaignDTO.getParticipants();
        List<AutonomousCommunityEntity> autonomousCommunityParticipantsEntities = autonomousCommunityConverter.convertToEntity(participantsList);
        autonomousCommunityParticipantsEntities.forEach(participant -> {
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

        return campaignSave;
    }

    @Override
    public CampaignEntity updateCampaign(Long idCampaing, CampaignDTO campaignDTO) {
//        CampaignEntity campaign = campaignRepository.findById(idCampaing).get();

        if (ObjectUtils.isEmpty(campaignDTO.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return createCampaign(campaignDTO);
    }


    public CampaignDTO findCampaignById(Long idCampaign) {
        CampaignEntity campaign = campaignRepository.findById(idCampaign)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró registro con ID: " + idCampaign));
        if(campaign != null){
            CampaignDTO campaignDTO = campaingnConverter.convertToModel(campaign);
            campaignDTO.setParticipants(autonomousCommunityParticipantsService.findByIdCampaign(idCampaign));
            campaignDTO.setProponents(autonomousCommunityProponentService.finByIdCampaign(idCampaign));
            campaignDTO.setSpecialists(autonomousCommunitySpecialistService.finByIdCampaign(idCampaign));

            return campaignDTO;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign not found");

        }

    }

}
