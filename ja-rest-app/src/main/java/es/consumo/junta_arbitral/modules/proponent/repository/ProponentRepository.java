package es.consumo.junta_arbitral.modules.proponent.repository;

import es.consumo.junta_arbitral.modules.proponent.model.entity.ProponentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;

@Repository
public interface ProponentRepository extends JJAARepository<ProponentEntity, Long>  {

    @Query(value = "SELECT h FROM ProponentEntity h where h.id = :id ")
    Page<ProponentEntity.SimpleProjection> findAllProponentById(Pageable pageable, @Param("id") Long id);

}
