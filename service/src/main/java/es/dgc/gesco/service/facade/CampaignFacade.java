package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.commom.dto.StatusChange;
import es.dgc.gesco.model.modules.ambit.converter.AmbitConverter;
import es.dgc.gesco.model.modules.autonomousCommunity.converter.AutonomousComunityConverter;
import es.dgc.gesco.model.modules.autonomousCommunityParticipants.db.entity.AutonomousCommunityParticipants;
import es.dgc.gesco.model.modules.autonomousCommunityProponent.db.entity.AutonomousCommunityProponent;
import es.dgc.gesco.model.modules.autonomousCommunitySpecialist.db.entity.AutonomousCommunitySpecialist;
import es.dgc.gesco.model.modules.campaignType.converter.CampaingnTypeConverter;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.campaign.converter.CampaingnConverter;
import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.model.modules.campaign.dto.CampaignDTO;

import es.dgc.gesco.model.modules.phase.converter.PhaseConverter;
import es.dgc.gesco.model.modules.phase.db.entity.PhaseCampaign;
import es.dgc.gesco.model.modules.phase.dto.PhaseCampaignDTO;
import es.dgc.gesco.model.modules.proponent.converter.ProponentConverter;
import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;
import es.dgc.gesco.model.modules.specialist.converter.SpecialistConverter;
import es.dgc.gesco.model.modules.specialist.db.entity.Specialist;
import es.dgc.gesco.service.service.CampaignService;
import es.dgc.gesco.service.util.Accion;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CampaignFacade {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaingnConverter campaingnConverter;

    @Autowired
    private CampaingnTypeConverter campaingnTypeConverter;

    @Autowired
    private PhaseCampaignFacade phaseCampaignFacade;

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
    public void saveCampaign(CampaignDTO campaignDto, Accion accion) {
        Campaign campaign = campaingnConverter.convertDtoToCampaign(campaignDto);
        campaign.setAutonomousCommunityResponsible(autonomousComunityConverter.convertDtoToAutonomousCommunity(campaignDto.getResponsibleEntity()));
        campaign.setCampaignType(campaingnTypeConverter.convertDtoToCampaingnType(campaignDto.getCampaignType()));
        campaign.setAmbit(ambitConverter.convertDtoToAmbit(campaignDto.getAmbit()));

        if (accion.equals(Accion.ADD)) {
            campaign.setPhaseCampaign(phaseCampaignFacade.getPhaseCampaignById(1L));
        }else if (accion.equals(Accion.UPDATE)){
            Campaign campaignActual = campaignService.getCampaignById(campaignDto.getId());
            campaign.setCreatedAt(campaignActual.getCreatedAt());
            campaign.setUpdatedAt(campaignActual.getUpdatedAt());
            campaign.setPhaseCampaign(campaignActual.getPhaseCampaign());
        }

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

    public void updateCampaign(final CampaignDTO campaignDto){

        valid(campaignDto, Accion.UPDATE);
        saveCampaign(campaignDto, Accion.UPDATE);
    }

    public CampaignDTO getCampaignById(final Long id){
        Campaign campaign = campaignService.getCampaignById(id);
        if(campaign != null) {
            CampaignDTO campaignDTO = campaingnConverter.convertCampaingnToDto(campaign);
            campaignDTO.setParticipants(autonomousCommunityParticipantFacade.findByCampaignId(id));
            campaignDTO.setProponents(autonomousCommunityProponentFacade.findByCampaignId(id));
            campaignDTO.setSpecialists(autonomousCommunitySpecialistFacade.findByCampaignId(id));
            return campaignDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign not found");
        }
    }

    public CampaignDTO changeStateCampaign(final Long id, final StatusChange statusChange){
        Campaign campaign = campaignService.changeStateCampaign(id, statusChange.getStatus());
        CampaignDTO campaignDTO = campaingnConverter.convertCampaingnToDto(campaign);
        return campaignDTO;
    }

    private Page<CampaignDTO> loadCampaignPageDto(Page<Campaign> campaignPage) {
        Page<CampaignDTO> campaingDtoPage = campaignPage.map(campaing -> campaingnConverter.convertCampaingnToDto(campaing));

        return campaingDtoPage;
    }

    private void valid(final CampaignDTO campaignDTO, final Accion accion){

        if (accion.equals(Accion.UPDATE)) {

            if (ObjectUtils.isEmpty(campaignDTO.getId()))
                throw new ResponseStatusException(HttpStatus.CONFLICT);

        }

    }

    public Page<CampaignDTO> getCampaignByNameOrYearOrCode(String nameCampaign, String codeCampaign, Long yearCampaign, Pageable pageable) {

            Page<Campaign> campaignPage;
            Page<CampaignDTO> campaignDTOPage;

        if( nameCampaign != null && yearCampaign != null && codeCampaign != null){

            campaignPage = campaignService.getCampaignByNameOrYearOrCode(nameCampaign, codeCampaign, yearCampaign, pageable);
            campaignDTOPage = campaignPage.map(campaign -> campaingnConverter.convertCampaingnToDto(campaign));
            return campaignDTOPage;

        }else if (nameCampaign != null && yearCampaign != null){
            campaignPage = campaignService.getCampaignByNameAndYear(nameCampaign, yearCampaign, pageable);
            campaignDTOPage = campaignPage.map(campaign -> campaingnConverter.convertCampaingnToDto(campaign));
            return campaignDTOPage;

        }else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Por favor, digite valores válidos para Nombre y Año de la Campaña.");
        }
    }

    public void updatePhaseCampaign(Long idCampaign) {
        Campaign campaign = campaignService.getCampaignById(idCampaign);
        if(campaign != null && campaign.getPhaseCampaign() != null) {
           Long limit = 14L;
           Long idPhaseOld = campaign.getPhaseCampaign().getId();
           if (idPhaseOld < limit){
               idPhaseOld++;
               PhaseCampaign phaseCampaign = phaseCampaignFacade.getPhaseCampaignById(idPhaseOld);
               campaign.setPhaseCampaign(phaseCampaign);
               campaignService.saveCampaign(campaign);
           }
        }
    }
}