package es.consumo.gescom.modules.campaign.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {

    private Long campaignId;

    private String protocolCode;

    private Long protocolId;

    private String productServiceCode;

    private Long productServiceId;

    private String iprCode;

    private Long iprId;

}
