package es.consumo.junta_arbitral.modules.autonomousCommunity.repository;

import es.consumo.junta_arbitral.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;

@Repository
public interface AutonomousCommunityRepository extends JJAARepository<AutonomousCommunityEntity, Long>  {

    @Query(value = "SELECT h FROM AutonomousCommunityEntity h "
        + " where h.name = :name ")
    Page<AutonomousCommunityEntity.SimpleProjection> findAllAutonomousCommunityByName(
    Pageable pageable,
    @Param("name") String name);

}
