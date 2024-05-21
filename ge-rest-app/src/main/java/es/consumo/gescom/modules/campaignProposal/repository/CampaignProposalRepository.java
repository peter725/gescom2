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

    @Query(value = "SELECT a FROM CampaignProposalEntity a, AutonomousCommunityEntity b, CampaignTypeEntity c "
            + "WHERE a.autonomousCommunityId = b.id AND a.campaignTypeId = c.id AND "
            + "(:#{#criteria.approach} is null OR UPPER(a.approach) LIKE :#{#criteria.approach}) "
            + "AND (:#{#criteria.autonomusCommunity} is null OR UPPER(b.name) LIKE :#{#criteria.autonomusCommunity}) "
            + "AND (:#{#criteria.type} is null OR UPPER(c.name) LIKE :#{#criteria.type}) "
            + "AND (:#{#criteria.year} is null OR YEAR(a.date) = :#{#criteria.year}) "
            + "AND (:#{#criteria.state} is null OR a.state in :#{#criteria.state}) "
            + "AND ("
            + ":#{#criteria.search} is null "
            + "OR UPPER(a.approach) like :#{#criteria.search} "
            + "OR cast(YEAR(a.date) as string) like :#{#criteria.search} "
            + "OR UPPER(c.name) like :#{#criteria.search} "
            + "OR UPPER(b.name) like :#{#criteria.search}) "
            )
    public Page<CampaignProposalEntity> findAllByCriteria(CampaignProposalCriteria criteria, Pageable pageable);

    @Query(value = "SELECT h FROM CampaignProposalEntity h where h.id = :id ")
    Page<CampaignProposalEntity.SimpleProjection> findAllCampaignProposalById(Pageable pageable, @Param("id") Long id);

    @Query(value ="SELECT ap FROM CampaignProposalEntity ap WHERE ap.date BETWEEN :localDateIni AND :localDateFin ")
    Page<CampaignProposalEntity.SimpleProjection> findCampaignProposalByYear(Pageable pageable, @Param("localDateIni") LocalDate localDateIni, @Param("localDateFin") LocalDate localDateFin);

    @Query(value ="SELECT ap FROM CampaignProposalEntity ap WHERE ap.autonomousCommunityId =:idCA ")
    Page<CampaignProposalEntity.SimpleProjection> findListByCriteriaAutonomousCommunityId(Pageable pageable, Long idCA);

}
