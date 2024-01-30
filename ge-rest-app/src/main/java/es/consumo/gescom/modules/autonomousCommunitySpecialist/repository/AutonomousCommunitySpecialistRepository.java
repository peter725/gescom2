package es.consumo.gescom.modules.autonomousCommunitySpecialist.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.autonomousCommunitySpecialist.model.criteria.AutonomousCommunitySpecialistCriteria;
import es.consumo.gescom.modules.autonomousCommunitySpecialist.model.entity.AutonomousCommunitySpecialistEntity;
import es.consumo.gescom.modules.specialist.model.dto.SpecialistDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutonomousCommunitySpecialistRepository extends GESCOMRepository<AutonomousCommunitySpecialistEntity, Long>, QueryByCriteria<AutonomousCommunitySpecialistEntity.SimpleProjection, AutonomousCommunitySpecialistCriteria> {

    @Query("SELECT p FROM AutonomousCommunitySpecialistEntity p WHERE p.campaign.id=:campaignId AND p.state=1")
    List<AutonomousCommunitySpecialistEntity> findByIdCampaign(@Param("campaignId")Long idCampaign);
}
