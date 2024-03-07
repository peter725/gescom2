package es.consumo.gescom.modules.ipr.model.dto;

import es.consumo.gescom.commons.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IprResponseDTO implements Serializable, LongIdModel {

    private Long id;
    private String code;
    private String protocolCode;
    private String name;
    private Long campaignId;
    private Long protocolId;

    private Integer orderQuestion;
    private Integer percentageRespectTo;
    private String formula;
    private String question;


}
