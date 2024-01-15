package es.consumo.gescom.modules.campaignProposal.repository;

import es.consumo.gescom.modules.campaignProposal.model.entity.CampaignProposalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.JJAARepository;

import java.time.LocalDate;

@Repository
public interface CampaignProposalRepository extends JJAARepository<CampaignProposalEntity, Long>  {

    @Query(value = "SELECT h FROM CampaignProposalEntity h where h.id = :id ")
    Page<CampaignProposalEntity.SimpleProjection> findAllCampaignProposalById(Pageable pageable, @Param("id") Long id);

    @Query(value ="SELECT ap FROM CampaignProposalEntity ap WHERE ap.date BETWEEN :localDateIni AND :localDateFin ")
    Page<CampaignProposalEntity.SimpleProjection> findCampaignProposalByYear(Pageable pageable, @Param("localDateIni") LocalDate localDateIni, @Param("localDateFin") LocalDate localDateFin);

    @Query(value ="SELECT ap FROM CampaignProposalEntity ap WHERE ap.autonomousCommunityId =:idCA ")
    Page<CampaignProposalEntity.SimpleProjection> findListByCriteriaAutonomousCommunityId(Pageable pageable, Long idCA);
}
