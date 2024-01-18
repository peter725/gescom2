package es.consumo.gescom.modules.proponent.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.proponent.model.criteria.ProponentCriteria;
import es.consumo.gescom.modules.proponent.model.entity.ProponentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

@Repository
public interface ProponentRepository extends GESCOMRepository<ProponentEntity, Long> , QueryByCriteria<ProponentEntity.SimpleProjection, ProponentCriteria> {

    @Query(value = "SELECT h FROM ProponentEntity h where h.id = :id ")
    Page<ProponentEntity.SimpleProjection> findAllProponentById(Pageable pageable, @Param("id") Long id);

}
