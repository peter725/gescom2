package es.consumo.gescom.modules.campaignProposal.model.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.dto.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignProposalCriteria extends FilterCriteria {

    private Long id;

    private Boolean sent;

    private LocalDate date;

    private Long autonomousCommunityId;

    private String autonomousCommunityName;

    private Long userId;

    private String approach;

    private Long campaignTypeId;

    private String campaignTypeName;

    private String justification;

    private String objective;

    private String viability;
 }
