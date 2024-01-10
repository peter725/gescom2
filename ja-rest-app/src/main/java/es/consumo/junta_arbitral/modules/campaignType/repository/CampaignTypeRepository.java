package es.consumo.junta_arbitral.modules.campaignType.repository;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.modules.campaignType.model.entity.CampaignTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignTypeRepository extends JJAARepository<CampaignTypeEntity, Long> {

    @Query(value = "SELECT h FROM CampaignTypeEntity h "
            + " where h.id = :id ")
    Page<CampaignTypeEntity.SimpleProjection> findAllCampaignTypeEntityById(
            Pageable pageable,
            @Param("id") Long id);
}