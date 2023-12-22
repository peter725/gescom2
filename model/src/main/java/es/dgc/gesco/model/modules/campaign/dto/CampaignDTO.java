package es.dgc.gesco.model.modules.campaign.dto;

import es.dgc.gesco.model.commom.dto.LongIdModel;
import es.dgc.gesco.model.modules.ambit.dto.AmbitDTO;
import es.dgc.gesco.model.modules.autonomousCommunity.dto.AutonomousComunityDTO;
import es.dgc.gesco.model.modules.phase.dto.PhaseCampaignDTO;
import es.dgc.gesco.model.modules.proponent.dto.ProponentDTO;
import es.dgc.gesco.model.modules.specialist.dto.SpecialistDTO;
import es.dgc.gesco.model.modules.CampaignType.dto.CampaignTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CampaignDTO implements LongIdModel {

    Long id;

    Long year;

    String cpa;

    String nameCampaign;

    CampaignTypeDTO campaignTypeDto;

    AmbitDTO ambitDto;

    AutonomousComunityDTO autonomousCommunityResponsibleDto;

    PhaseCampaignDTO phaseCampaignDto;

    List<AutonomousComunityDTO> participantsDtoList;

    List<ProponentDTO> proponentDTOList;

    List<SpecialistDTO> specialistDTOList;

}
