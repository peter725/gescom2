package es.consumo.junta_arbitral.modules.ambit.repository;

import es.consumo.junta_arbitral.modules.ambit.model.entity.AmbitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;

@Repository
public interface AmbitRepository extends JJAARepository<AmbitEntity, Long>  {

    @Query(value = "SELECT h FROM AmbitEntity h where h.id = :id ")
    Page<AmbitEntity.SimpleProjection> findAllAmbitById(Pageable pageable, @Param("id") Long id);

}
