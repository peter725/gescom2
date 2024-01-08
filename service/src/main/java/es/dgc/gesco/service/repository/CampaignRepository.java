package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.model.modules.campaign.dto.criteria.CampaignCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends BaseRepository<Campaign, Long>, QueryByCriteria<Campaign, CampaignCriteria> {

    @Query(value = "SELECT cp FROM Campaign cp WHERE cp.nameCampaign LIKE %:nameCampaign% AND cp.codeCpa LIKE %:codeCampaign% AND cp.year = :yearCampaign")
    Page<Campaign> getCampaignByNameOrYearOrCode(@Param("nameCampaign") String nameCampaign, @Param("codeCampaign") String codeCampaign, @Param("yearCampaign") Long yearCampaign, Pageable pageable);

    @Query(value ="SELECT cp FROM Campaign cp WHERE cp.nameCampaign like %:nameCampaign% AND cp.year =:yearCampaign ")
    Page<Campaign> getCampaignByNameAndYear(@Param("nameCampaign") String nameCampaign, @Param("yearCampaign") Long yearCampaign, Pageable pageable);
}