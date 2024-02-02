package es.consumo.gescom.modules.infringement.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.infringement.model.entity.InfringementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InfringementRepository extends GESCOMRepository<InfringementEntity, Long> {

    @Query(value = "SELECT h FROM InfringementEntity h where h.id = :id ")
    Page<InfringementEntity.SimpleProjection> findAllInfringementEntityById(Pageable pageable, @Param("id") Long id);
}