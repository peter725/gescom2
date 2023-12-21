package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.ambit.db.entity.Ambit;
import es.dgc.gesco.model.modules.ambit.dto.criteria.AmbitCriteria;
import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.model.modules.campaign.dto.criteria.CampaignCriteria;
import org.springframework.stereotype.Repository;


@Repository
public interface AmbitRepository extends BaseRepository<Ambit, Long>, QueryByCriteria<Ambit, AmbitCriteria> {

}