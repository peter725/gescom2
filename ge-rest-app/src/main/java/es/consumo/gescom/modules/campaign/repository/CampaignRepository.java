package es.consumo.gescom.modules.campaign.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends GESCOMRepository<CampaignEntity, Long>, QueryByCriteria<CampaignEntity, CampaignCriteria> {

    @Override
    @Query(value = "SELECT a FROM CampaignEntity a "
            + "WHERE "
            + "(:#{#criteria.search} is null OR UPPER(a.nameCampaign) LIKE :#{#criteria.search}) "
    )
    Page<CampaignEntity> findAllByCriteria(CampaignCriteria criteria, Pageable pageable);


    @Override
    Page<CampaignEntity> findAll(Pageable pageable);
}
