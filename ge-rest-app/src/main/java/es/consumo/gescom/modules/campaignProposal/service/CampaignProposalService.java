package es.consumo.gescom.modules.campaignProposal.service;

import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.campaignProposal.model.criteria.CampaignProposalCriteria;
import es.consumo.gescom.modules.campaignProposal.model.dto.CampaignProposalDTO;
import es.consumo.gescom.modules.campaignProposal.model.entity.CampaignProposalEntity;
import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;

public interface CampaignProposalService extends CrudService<CampaignProposalEntity, Long>{

    CampaignProposalDTO createCampaignProposal(CampaignProposalDTO campaignProposalDTO);

    Page<CampaignProposalDTO> findAllCampaignProposal(CriteriaWrapper<?> criteriaWrapper);

    CampaignProposalDTO findCampaignProposalById(Long id);

    CampaignProposalEntity switchStatus(ChangeStatusDTO changeStatusDTO, Long id);

    CampaignProposalEntity update(CampaignProposalDTO payload);

    Page<CampaignProposalEntity.SimpleProjection> findCampaignProposalByYear(CriteriaWrapper<CampaignProposalCriteria> wrapper, int year);

    Page<CampaignProposalEntity.SimpleProjection> findListByCriteriaAutonomousCommunityId(CriteriaWrapper<CampaignProposalCriteria> campaignProposalCriteriaCriteriaWrapper, Long idCA);
}
