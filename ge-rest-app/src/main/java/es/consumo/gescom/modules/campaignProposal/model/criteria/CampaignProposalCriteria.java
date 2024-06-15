package es.consumo.gescom.modules.campaignProposal.model.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.dto.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignProposalCriteria extends FilterCriteria {

    private Integer year;
    private String autonomusCommunity;
    private String approach;
    private String type;
    
    
 }
