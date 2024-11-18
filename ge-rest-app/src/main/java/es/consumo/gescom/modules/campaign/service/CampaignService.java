package es.consumo.gescom.modules.campaign.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.campaign.model.dto.*;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.phase.model.dto.PhaseDTO;
import es.consumo.gescom.commons.service.CrudService;
import org.springframework.data.domain.Page;

public interface CampaignService extends CrudService<CampaignEntity, Long>{

    CampaignDTO createCampaign(CampaignDTO campaignDTO);

    CampaignDTO updateCampaign(Long id, CampaignDTO campaignDTO);

    CampaignDTO findCampaignById(Long idCampaignDTO);

    Page<CampaignDTO> performFindAllCampaing(CriteriaWrapper<?> criteriaWrapper);

    CampaignEntity switchStatus(ChangeStatusDTO changeStatus, Long id);

    /*ResultsResponseDTO getResults(SearchDTO searchDTO);*/

    CampaignEntity switchPhase(PhaseDTO changeStatus, Long id);
}
