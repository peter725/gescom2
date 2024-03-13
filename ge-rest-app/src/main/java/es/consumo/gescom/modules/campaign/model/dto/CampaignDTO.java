package es.consumo.gescom.modules.campaign.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import es.consumo.gescom.commons.constants.EntityState;
import es.consumo.gescom.modules.ambit.model.dto.AmbitDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.campaignProductService.model.dto.CampaignProductServiceDTO;
import es.consumo.gescom.modules.campaignType.model.dto.CampaignTypeDTO;
import es.consumo.gescom.modules.phase.model.dto.PhaseDTO;
import es.consumo.gescom.modules.productServices.model.dto.ProductServiceDTO;
import es.consumo.gescom.modules.proponent.model.dto.ProponentDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsDTO;
import es.consumo.gescom.modules.specialist.model.dto.SpecialistDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<CampaignProductServiceDTO> campaignProductServiceDTOS;
    private List<ProtocolResultsDTO> protocolResultsDTOS;
    private Integer state = EntityState.ON.getValue();
    private ResultsResponseDTO resultsResponseDTO;

    //lista de productos
}
