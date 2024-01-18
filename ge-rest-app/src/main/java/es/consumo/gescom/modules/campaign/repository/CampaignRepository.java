package es.consumo.gescom.modules.campaign.repository;

import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

@Repository
public interface CampaignRepository extends GESCOMRepository<CampaignEntity, Long> {

    @Query(value = "SELECT h FROM CampaignEntity h where h.id = :id ")
    Page<CampaignEntity.SimpleProjection> updatePhaseCampaign(Pageable pageable, @Param("id")  Long id);
}
