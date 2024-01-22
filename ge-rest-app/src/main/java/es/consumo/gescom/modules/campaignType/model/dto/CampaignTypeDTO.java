package es.consumo.gescom.modules.campaignType.model.dto;

import es.consumo.gescom.commons.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CampaignTypeDTO implements Serializable, LongIdModel {

    private Long id;
    private String name;

}
