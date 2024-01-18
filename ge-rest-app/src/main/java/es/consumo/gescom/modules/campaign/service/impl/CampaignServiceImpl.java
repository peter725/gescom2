package es.consumo.gescom.modules.campaign.service.impl;

import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.arbitration.model.constants.ClaimantType;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitrationBoard.model.criteria.ArbitrationBoardCriteria;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import es.consumo.gescom.modules.arbitrationBoard.repository.ArbitrationBoardRepository;
import es.consumo.gescom.modules.arbitrationBoard.service.ArbitrationBoardService;
import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.repository.CampaignRepository;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;


@Service
public class CampaignServiceImpl extends EntityCrudService<CampaignEntity, Long> implements CampaignService {
    protected CampaignServiceImpl(GESCOMRepository<CampaignEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private CampaignRepository campaignRepository;


}
