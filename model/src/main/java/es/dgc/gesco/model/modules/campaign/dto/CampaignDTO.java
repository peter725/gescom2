package es.dgc.gesco.model.modules.campaign.dto;

import es.dgc.gesco.model.commom.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CampaignDTO implements LongIdModel {

    Long id;
    Long year;
    String cpa;
    String name_campaign;
    Long id_type_campaign;
    Long id_field;
    Long id_autonomous_community;
    Long id_phase_campaign;


}
