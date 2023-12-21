package es.dgc.gesco.model.modules.CampaignType.dto;

import es.dgc.gesco.model.commom.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CampaignTypeDto implements LongIdModel {

    Long id;

    String type;


}