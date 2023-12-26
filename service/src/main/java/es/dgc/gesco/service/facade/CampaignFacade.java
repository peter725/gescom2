package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.ambit.converter.AmbitConverter;
import es.dgc.gesco.model.modules.autonomousCommunity.converter.AutonomousComunityConverter;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunityParticipants;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunityProponent;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunitySpecialist;
import es.dgc.gesco.model.modules.campaignType.converter.CampaingnTypeConverter;
import es.dgc.gesco.model.modules.campaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.campaignType.dto.CampaignTypeDTO;
import es.dgc.gesco.model.modules.campaign.converter.CampaingnConverter;
import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.model.modules.campaign.dto.CampaignDTO;

import es.dgc.gesco.model.modules.phase.converter.PhaseConverter;
import es.dgc.gesco.model.modules.proponent.converter.ProponentConverter;
import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;
import es.dgc.gesco.model.modules.specialist.converter.SpecialistConverter;
import es.dgc.gesco.model.modules.specialist.db.entity.Specialist;
import es.dgc.gesco.service.service.CampaignService;
import es.dgc.gesco.service.service.CampaignTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampaignFacade {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaingnConverter campaingnConverter;

    @Autowired
    private CampaingnTypeConverter campaingnTypeConverter;

    @Autowired
    private CampaignTypeService campaignTypeService;

    @Autowired
    private AutonomousComunityConverter autonomousComunityConverter;

    @Autowired
    private PhaseConverter phaseConverter;

    @Autowired
    private AmbitConverter ambitConverter;

    @Autowired
    private SpecialistConverter specialistConverter;

    @Autowired
    private ProponentConverter proponentConverter;

    @Autowired
    private AutonomousCommunityParticipantFacade autonomousCommunityParticipantFacade;

    @Autowired
    private AutonomousCommunityProponentFacade autonomousCommunityProponentFacade;

    @Autowired
    private AutonomousCommunitySpecialistFacade autonomousCommunitySpecialistFacade;


    public Page<CampaignDTO> getAllCampaign(Pageable pageable) {
        Page<Campaign> campaignPage = campaignService.getAllByPage(pageable);

        return  loadCampaignPageDto(campaignPage);
    }

    @Transactional
    public void saveCampaign(CampaignDTO campaignDto) {
        Campaign campaign = campaingnConverter.convertDtoToCampaign(campaignDto);
        campaign.setAutonomousCommunityResponsible(autonomousComunityConverter.convertDtoToAutonomousCommunity(campaignDto.getResponsibleEntity()));
        campaign.setPhase(phaseConverter.convertDtoToPhase(campaignDto.getPhaseCampaignDto()));
        campaign.setTypeCampaign(campaingnTypeConverter.convertDtoToCampaingnType(campaignDto.getCampaignType()));
        campaign.setAmbit(ambitConverter.convertDtoToAmbit(campaignDto.getAmbit()));

        Campaign campaignSave = campaignService.saveCampaign(campaign);

        List<AutonomousCommunity> participantsList = campaignDto.getParticipants().stream().map(autonomousComunityDto -> {
            AutonomousCommunity autonomousCommunity = autonomousComunityConverter.convertDtoToAutonomousCommunity(autonomousComunityDto);
            return autonomousCommunity;
        }).toList();

        List<Proponent> proponentsList = campaignDto.getProponents().stream().map(proponentDto -> {
            Proponent proponent = proponentConverter.convertProponentDtoToEntity(proponentDto);
            return proponent;
        }).toList();

        List<Specialist> specialistsList = campaignDto.getSpecialists().stream().map(specialistDto -> {
            Specialist specialist = specialistConverter.convertSpecialistDtoToEntity(specialistDto);
            return specialist;
        }).toList();

        participantsList.forEach(participant -> {
            AutonomousCommunityParticipants autonomousCommunityParticipants = new AutonomousCommunityParticipants();
            autonomousCommunityParticipants.setCampaign(campaignSave);
            autonomousCommunityParticipants.setAutonomousCommunity(participant);
            autonomousCommunityParticipantFacade.save(autonomousCommunityParticipants);
        });

        proponentsList.forEach(proponent -> {
            AutonomousCommunityProponent autonomousCommunityProponent = new AutonomousCommunityProponent();
            autonomousCommunityProponent.setCampaign(campaignSave);
            autonomousCommunityProponent.setProponent(proponent);
            autonomousCommunityProponentFacade.save(autonomousCommunityProponent);
        });

        specialistsList.forEach(specialist -> {
            AutonomousCommunitySpecialist autonomousCommunitySpecialist = new AutonomousCommunitySpecialist();
            autonomousCommunitySpecialist.setCampaign(campaignSave);
            autonomousCommunitySpecialist.setSpecialist(specialist);
            autonomousCommunitySpecialistFacade.save(autonomousCommunitySpecialist);
        });


    }

    private Page<CampaignDTO> loadCampaignPageDto(Page<Campaign> campaignPage) {
        Page<CampaignDTO> campaingDtoPage = campaignPage.map(campaing -> campaingnConverter.convertCampaingnToDto(campaing));

        return campaingDtoPage;
    }

    private CampaignTypeDTO loadCampaignTypeDto(CampaignType campaignType) {
        CampaignTypeDTO campaignTypeDto = campaingnTypeConverter.convertCampaingnTypeToDto(campaignType);
        return campaignTypeDto;
    }

}