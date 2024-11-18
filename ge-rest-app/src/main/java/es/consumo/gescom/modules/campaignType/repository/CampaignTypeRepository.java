package es.consumo.gescom.modules.campaignType.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.campaignType.model.criteria.CampaignTypeCriteria;
import es.consumo.gescom.modules.campaignType.model.entity.CampaignTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignTypeRepository extends GESCOMRepository<CampaignTypeEntity, Long> {

    @Query(value = "SELECT h FROM CampaignTypeEntity h where h.id = :id ")
    Page<CampaignTypeEntity.SimpleProjection> findAllCampaignTypeEntityById( Pageable pageable, @Param("id") Long id);
}