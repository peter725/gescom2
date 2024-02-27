package es.consumo.gescom.modules.totalProtocolResults.model.dto;

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
public class TotalProtocolResultsDTO implements Serializable, LongIdModel {

    private Long id;
    private Long ccaa_ren;
    private Long ccaa_rep;
    private Long ccaa_res;
    private String code;
    private String protocolResultsCode;
    private String codeQuestion;
    private Long protocolResultsId;


}
