package es.consumo.gescom.modules.autonomousCommunity.repository;

import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

@Repository
public interface AutonomousCommunityRepository extends GESCOMRepository<AutonomousCommunityEntity, Long> {

    @Query(value = "SELECT h FROM AuthorityDGCEntity h  where h.name = :name ")
    Page<AutonomousCommunityEntity.SimpleProjection> findAllAutonomousCommunityByName( Pageable pageable, @Param("name") String name);
    
    @Query(value = "SELECT a.id FROM AutonomousCommunityEntity a WHERE a.name = :name")
    Long findIdByName(String name);

}
