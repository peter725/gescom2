package es.consumo.gescom.modules.sumProtocol.repository;

import es.consumo.gescom.modules.sumProtocol.model.entity.SumProtocolEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

@Repository
public interface SumProtocolRepository extends GESCOMRepository<SumProtocolEntity, Long> {

    @Query(value = "SELECT h FROM SumProtocolEntity h where h.id = :id ")
    Page<SumProtocolEntity.SimpleProjection> findAllSumProtocolById(Pageable pageable, @Param("id") Long id);

}
