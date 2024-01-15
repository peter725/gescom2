package es.consumo.gescom.modules.campaignProposal.service.impl;

import es.consumo.gescom.modules.campaignProposal.model.criteria.CampaignProposalCriteria;
import es.consumo.gescom.modules.campaignProposal.model.entity.CampaignProposalEntity;
import es.consumo.gescom.modules.campaignProposal.repository.CampaignProposalRepository;
import es.consumo.gescom.modules.campaignProposal.service.CampaignProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;

import java.time.LocalDate;


@Service
public class CampaignProposalServiceImpl extends EntityCrudService<CampaignProposalEntity, Long> implements CampaignProposalService {
    protected CampaignProposalServiceImpl(JJAARepository<CampaignProposalEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private CampaignProposalRepository campaignProposalRepository;

    @Override
    public Page<CampaignProposalEntity.SimpleProjection> findAllCampaignProposalById(CriteriaWrapper<CampaignProposalCriteria> wrapper, Long id) {
        return ((CampaignProposalRepository) repository).findAllCampaignProposalById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public Page<CampaignProposalEntity.SimpleProjection> findCampaignProposalByYear(CriteriaWrapper<CampaignProposalCriteria> wrapper, int year) {
        LocalDate localDateIni = LocalDate.ofYearDay(year, 1);
        LocalDate localDateFin = LocalDate.ofYearDay(year, LocalDate.of(year, 12, 31).getDayOfYear());

        return ((CampaignProposalRepository) repository).findCampaignProposalByYear(wrapper.getCriteria().toPageable(), localDateIni, localDateFin);
    }

    @Override
    public Page<CampaignProposalEntity.SimpleProjection> findListByCriteriaAutonomousCommunityId(CriteriaWrapper<CampaignProposalCriteria> wrapper, Long id) {
        return ((CampaignProposalRepository) repository).findListByCriteriaAutonomousCommunityId(wrapper.getCriteria().toPageable(), id);
    }
}
