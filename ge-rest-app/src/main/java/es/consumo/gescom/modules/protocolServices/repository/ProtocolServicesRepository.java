package es.consumo.gescom.modules.protocolServices.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.protocolServices.model.entity.ProtocolServicesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocolServicesRepository extends GESCOMRepository<ProtocolServicesEntity, Long> {

    @Query(value = "SELECT h FROM ProtocolServicesEntity h where h.id = :id ")
    Page<ProtocolServicesEntity.SimpleProjection> findAllProtocolServicesById(Pageable pageable, @Param("id") Long id);

}
