package es.consumo.gescom.modules.campaign.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.constants.EntityState;
import es.consumo.gescom.modules.ambit.model.dto.AmbitDTO;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.dto.AutonomousCommunityParticipantsDTO;
import es.consumo.gescom.modules.campaignType.model.dto.CampaignTypeDTO;
import es.consumo.gescom.modules.campaignType.model.entity.CampaignTypeEntity;
import es.consumo.gescom.modules.phase.model.dto.PhaseDTO;
import es.consumo.gescom.modules.phase.model.entity.PhaseEntity;
import es.consumo.gescom.modules.proponent.model.dto.ProponentDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.specialist.model.dto.SpecialistDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDTO implements Serializable {

    private Long id;
    private Long year;
    private String codeCpa;
    private String nameCampaign;
    private CampaignTypeDTO campaignType; /// DTO
    private AmbitDTO ambit; /// DTO
    private AutonomousCommunityDTO autonomousCommunityResponsible; /// DTO
    private List<AutonomousCommunityDTO> participants;
    private List<ProponentDTO> proponents;
    private List<SpecialistDTO> specialists;
    private List<ProtocolDTO> protocols; /// DTO
    private PhaseDTO phaseCampaign;   /// DTO
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
    private Integer state = EntityState.ON.getValue();
}
