package es.consumo.gescom.modules.specialist.repository;

import es.consumo.gescom.modules.specialist.model.entity.SpecialistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.JJAARepository;

@Repository
public interface SpecialistRepository extends JJAARepository<SpecialistEntity, Long>  {

    @Query(value = "SELECT h FROM SpecialistEntity h where h.id = :id ")
    Page<SpecialistEntity.SimpleProjection> findAllSpecialistById(Pageable pageable, @Param("id") Long id);

}
