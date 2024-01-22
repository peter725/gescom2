package es.consumo.gescom.modules.autonomousCommunityProponent.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.autonomousCommunityProponent.model.criteria.AutonomousCommunityProponentCriteria;
import es.consumo.gescom.modules.autonomousCommunityProponent.model.entity.AutonomousCommunityProponentEntity;
import es.consumo.gescom.modules.proponent.model.dto.ProponentDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutonomousCommunityProponentRepository extends GESCOMRepository<AutonomousCommunityProponentEntity, Long>, QueryByCriteria<AutonomousCommunityProponentEntity.SimpleProjection, AutonomousCommunityProponentCriteria> {

    @Query("SELECT p FROM AutonomousCommunityProponentEntity p WHERE p.campaign.id=:campaignId AND p.state=1")
    List<AutonomousCommunityProponentEntity> finByIdCampaign(@Param("campaignId") Long idCampaign);
}
