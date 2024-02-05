package es.consumo.gescom.modules.protocol.model.dto;

import java.io.Serializable;

import es.consumo.gescom.commons.dto.LongIdModel;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolDTO implements Serializable, LongIdModel{

    private Long id;
    private String code;
    private String name;
    private CampaignEntity campaignId;
    
}
