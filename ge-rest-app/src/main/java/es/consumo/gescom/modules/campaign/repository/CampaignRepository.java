package es.consumo.gescom.modules.campaign.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

@Repository
public interface CampaignRepository extends GESCOMRepository<CampaignEntity, Long>, QueryByCriteria<CampaignEntity.SimpleProjection, CampaignCriteria> {
    @Query(value = "SELECT a FROM CampaignEntity a ")
    Page<CampaignEntity.SimpleProjection> findAllByCriteria(CampaignCriteria criteria, Pageable pageable);


}
