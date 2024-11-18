package es.consumo.gescom.modules.protocol.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.document.model.criteria.DocumentCriteria;
import es.consumo.gescom.modules.document.model.entity.DocumentEntity;
import es.consumo.gescom.modules.protocol.model.criteria.ProtocolCriteria;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.modules.users.model.criteria.UserCriteria;
import es.consumo.gescom.modules.users.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProtocolRepository extends GESCOMRepository<ProtocolEntity, Long>,  QueryByCriteria<ProtocolEntity.SimpleProjection, ProtocolCriteria> {

    @Query(value = "SELECT pr FROM ProtocolEntity pr WHERE pr.name LIKE %:protocol% AND pr.code LIKE %:code% ")
    Page<ProtocolEntity> getProtocolByNameOrCode(Pageable pageable, @Param("protocol") String protocol, @Param("code") String code);

    @Query(value = "SELECT pr FROM ProtocolEntity pr WHERE pr.campaignId.id = :idCampaign  ")
    List<ProtocolEntity> findProtocolByCampaignId(Long idCampaign);

    @Query(value = "SELECT pr FROM ProtocolEntity pr WHERE pr.code =:code ")
    Optional<ProtocolEntity> findProtocoloByCode(String code);

    @Query(value = "SELECT pr FROM ProtocolEntity pr WHERE pr.code =:code ")
    ProtocolEntity findProtocolByCode(String code);

    @Query(value = "SELECT pr FROM ProtocolEntity pr WHERE pr.id =:id ")
    ProtocolEntity findProtocolById(Long id);

    @Override
    @Query(value = "SELECT pr FROM ProtocolEntity pr "
            + "WHERE "
            + "(:#{#criteria.code} is null OR UPPER(pr.code) LIKE :#{#criteria.code}) "
            + "AND (:#{#criteria.name} is null OR UPPER(TRIM(pr.name)) LIKE :#{#criteria.name}) "
            + "AND (:#{#criteria.search} is null OR UPPER(TRIM(pr.name)) LIKE :#{#criteria.search}) "
            + "AND (:#{#criteria.campaignId} is null OR pr.campaignId.id in :#{#criteria.campaignId}) "

    )
    public Page<ProtocolEntity.SimpleProjection> findAllByCriteria(ProtocolCriteria criteria, Pageable pageable);
}
