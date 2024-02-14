package es.consumo.gescom.modules.campaignProduct.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.campaignProduct.model.entity.CampaignProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignProductRepository extends GESCOMRepository<CampaignProductEntity, Long> {

    @Query(value = "SELECT h FROM CampaignProductEntity h where h.id = :id ")
    Page<CampaignProductEntity.SimpleProjection> findAllCampaignProductById(Pageable pageable, Long id);
}
