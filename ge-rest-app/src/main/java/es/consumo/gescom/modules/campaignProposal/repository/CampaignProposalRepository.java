package es.consumo.gescom.modules.campaignProposal.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.campaignProposal.model.criteria.CampaignProposalCriteria;
import es.consumo.gescom.modules.campaignProposal.model.entity.CampaignProposalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

import java.time.LocalDate;

@Repository
public interface CampaignProposalRepository extends GESCOMRepository<CampaignProposalEntity, Long>, QueryByCriteria<CampaignProposalEntity, CampaignProposalCriteria> {

    @Query(value = "SELECT a FROM CampaignProposalEntity a "
            + "WHERE "
            + "(:#{#criteria.search} is null OR UPPER(a.approach) LIKE :#{#criteria.search}) "
    )
    public Page<CampaignProposalEntity> findAllByCriteria(CampaignProposalCriteria criteria, Pageable pageable);

    @Query(value = "SELECT h FROM CampaignProposalEntity h where h.id = :id ")
    Page<CampaignProposalEntity.SimpleProjection> findAllCampaignProposalById(Pageable pageable, @Param("id") Long id);

    @Query(value ="SELECT ap FROM CampaignProposalEntity ap WHERE ap.date BETWEEN :localDateIni AND :localDateFin ")
    Page<CampaignProposalEntity.SimpleProjection> findCampaignProposalByYear(Pageable pageable, @Param("localDateIni") LocalDate localDateIni, @Param("localDateFin") LocalDate localDateFin);

    @Query(value ="SELECT ap FROM CampaignProposalEntity ap WHERE ap.autonomousCommunityId =:idCA ")
    Page<CampaignProposalEntity.SimpleProjection> findListByCriteriaAutonomousCommunityId(Pageable pageable, Long idCA);

}
