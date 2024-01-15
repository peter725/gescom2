package es.consumo.gescom.modules.autonomousCommunity.repository;

import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.JJAARepository;

@Repository
public interface AutonomousCommunityRepository extends JJAARepository<AutonomousCommunityEntity, Long>  {

    @Query(value = "SELECT h FROM AuthorityDGCEntity h "
        + " where h.name = :name ")
    Page<AutonomousCommunityEntity.SimpleProjection> findAllAutonomousCommunityByName(
    Pageable pageable,
    @Param("name") String name);

}
