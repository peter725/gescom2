package es.consumo.gescom.modules.protocol.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.protocol.model.criteria.ProtocolCriteria;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProtocolRepository extends GESCOMRepository<ProtocolEntity, Long>{

    @Query(value = "SELECT pr FROM ProtocolEntity pr WHERE pr.name LIKE %:protocol% AND pr.code LIKE %:code% ")
    Page<ProtocolEntity> getProtocolByNameOrCode(Pageable pageable, @Param("protocol") String protocol, @Param("code") String code);

    @Query(value = "SELECT pr FROM ProtocolEntity pr WHERE pr.campaignId.id = :idCampaign  ")
    List<ProtocolEntity> findProtocolByCampaignId(Long idCampaign);
}
