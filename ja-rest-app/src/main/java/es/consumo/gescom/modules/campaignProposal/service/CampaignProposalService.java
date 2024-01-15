package es.consumo.gescom.modules.campaignProposal.service;

import es.consumo.gescom.modules.campaignProposal.model.criteria.CampaignProposalCriteria;
import es.consumo.gescom.modules.campaignProposal.model.entity.CampaignProposalEntity;
import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;

public interface CampaignProposalService extends CrudService<CampaignProposalEntity, Long>{

    Page<CampaignProposalEntity.SimpleProjection> findAllCampaignProposalById(CriteriaWrapper<CampaignProposalCriteria> wrapper, Long id);

    Page<CampaignProposalEntity.SimpleProjection> findCampaignProposalByYear(CriteriaWrapper<CampaignProposalCriteria> wrapper, int year);

    Page<CampaignProposalEntity.SimpleProjection> findListByCriteriaAutonomousCommunityId(CriteriaWrapper<CampaignProposalCriteria> campaignProposalCriteriaCriteriaWrapper, Long idCA);
}
