package es.consumo.gescom.modules.ipr.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IprRepository extends GESCOMRepository<IprEntity, Long> {

    @Query(value = "SELECT h FROM IprEntity h where h.id = :id ")
    Page<IprEntity.SimpleProjection> findAllIprById(Pageable pageable, @Param("id") Long id);

}
