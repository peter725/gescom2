package es.consumo.gescom.modules.ipr.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AmbitRepository extends GESCOMRepository<AmbitEntity, Long> {

    @Query(value = "SELECT h FROM CountryEntity h where h.id = :id ")
    Page<AmbitEntity.SimpleProjection> findAllAmbitById(Pageable pageable, @Param("id") Long id);

}
