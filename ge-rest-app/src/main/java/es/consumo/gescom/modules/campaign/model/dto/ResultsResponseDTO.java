package es.consumo.gescom.modules.campaign.model.dto;

import es.consumo.gescom.commons.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultsResponseDTO implements Serializable, LongIdModel {

    private Long id;
    private String campaignName;
    private String protocolName;
    private String productName;

    List<QuestionsResponseDTO> questionsResponseDTOS;
}
