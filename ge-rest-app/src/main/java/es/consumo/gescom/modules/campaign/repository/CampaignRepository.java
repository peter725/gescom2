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
public interface CampaignRepository extends GESCOMRepository<CampaignEntity, Long>, QueryByCriteria<CampaignEntity, CampaignCriteria> {

//    @Override
    @Query(value = "SELECT a FROM CampaignEntity a "
            + "WHERE "
            + "(:#{#criteria.search} is null OR UPPER(a.nameCampaign) LIKE :#{#criteria.search}) "
    )
    Page<CampaignEntity> findAllByCriteriaOld(CampaignCriteria criteria, Pageable pageable);
    
    @Override
    @Query(value = "SELECT a FROM CampaignEntity a "
        + "WHERE "
        + "(:#{#criteria.ambit} is null OR UPPER(a.ambit.name) LIKE :#{#criteria.ambit}) "
        + "AND (:#{#criteria.campaignType} is null OR UPPER(a.campaignType.name) LIKE :#{#criteria.campaignType}) "
        + "AND (:#{#criteria.nameCampaign} is null OR UPPER(a.nameCampaign) LIKE :#{#criteria.nameCampaign}) "
        + "AND (:#{#criteria.phaseCampaign} is null OR UPPER(a.phaseCampaign.description) LIKE :#{#criteria.phaseCampaign}) "
        + "AND (:#{#criteria.year} is null OR a.year = :#{#criteria.year}) "
        + "AND ("
        + ":#{#criteria.search} is null "
        + "OR UPPER(a.ambit.name) like :#{#criteria.search} )"
        + "OR UPPER(a.campaignType.name) LIKE :#{#criteria.search} "
        + "OR UPPER(a.nameCampaign) LIKE :#{#criteria.search} "
        + "OR UPPER(a.phaseCampaign.description) LIKE :#{#criteria.search} "
        )
    public Page<CampaignEntity> findAllByCriteria(CampaignCriteria criteria, Pageable pageable);


    @Override
    Page<CampaignEntity> findAll(Pageable pageable);
}
