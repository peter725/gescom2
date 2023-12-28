package es.dgc.gesco.model.modules.campaign.dto;

import es.dgc.gesco.model.commom.dto.LongIdModel;
import es.dgc.gesco.model.modules.ambit.dto.AmbitDTO;
import es.dgc.gesco.model.modules.autonomousCommunity.dto.AutonomousComunityDTO;
import es.dgc.gesco.model.modules.phase.dto.PhaseCampaignDTO;
import es.dgc.gesco.model.modules.campaignType.dto.CampaignTypeDTO;
import es.dgc.gesco.model.modules.proponent.dto.ProponentDTO;
import es.dgc.gesco.model.modules.specialist.dto.SpecialistDTO;
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

    String codeCpa;

    String nameCampaign;

    CampaignTypeDTO campaignType;

    AmbitDTO ambit;

    PhaseCampaignDTO phaseCampaign;

    AutonomousComunityDTO responsibleEntity;

    PhaseCampaignDTO phaseCampaignDto;

    List<AutonomousComunityDTO> participants;

    List<ProponentDTO> proponents;

    List<SpecialistDTO> specialists;

    Integer state;
}
