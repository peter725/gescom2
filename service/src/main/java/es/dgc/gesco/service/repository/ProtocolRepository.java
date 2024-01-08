package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.protocol.db.entity.Protocol;
import es.dgc.gesco.model.modules.protocol.dto.criteria.ProtocolCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocolRepository extends BaseRepository<Protocol, Long>, QueryByCriteria<Protocol, ProtocolCriteria> {

    @Query(value = "SELECT pr FROM Protocol pr WHERE pr.protocol LIKE %:protocol% AND pr.code LIKE %:code% ")
    Page<Protocol> getProtocolByNameOrCode(String protocol, String code, Pageable pageable);

}
