package es.consumo.gescom.modules.protocol.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import es.consumo.gescom.modules.campaign.model.dto.ResultsResponseDTO;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolDTO implements Serializable{

    private Long id;
    private String code;
    private String name;
    private Long campaignId;
    private LocalDateTime createdAt;
    private List<QuestionsDTO> question;
    private List<IprDTO> iprDTOS;
    private ResultsResponseDTO resultsResponseDTO;
    private String nameCampaign;
}
