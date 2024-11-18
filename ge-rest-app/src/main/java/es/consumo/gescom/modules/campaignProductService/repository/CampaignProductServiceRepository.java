package es.consumo.gescom.modules.campaignProductService.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.campaignProductService.model.entity.CampaignProductServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignProductServiceRepository extends GESCOMRepository<CampaignProductServiceEntity, Long> {

    @Query(value = "SELECT h FROM CampaignProductServiceEntity h where h.id = :id ")
    Page<CampaignProductServiceEntity.SimpleProjection> findAllCampaignProductById(Pageable pageable, @Param("id") Long id);

    @Query(value = "SELECT h FROM CampaignProductServiceEntity h where h.campaignId = :id and h.state = 1")
    List<CampaignProductServiceEntity> findCampaignProductServiceByCampaignId(@Param("id") Long id);

    @Modifying
    @Query("UPDATE CampaignProductServiceEntity e SET e.state = 2 WHERE e.id = :id")
    CampaignProductServiceEntity deleteByIdCPSE(@Param("id") Integer id);
}
