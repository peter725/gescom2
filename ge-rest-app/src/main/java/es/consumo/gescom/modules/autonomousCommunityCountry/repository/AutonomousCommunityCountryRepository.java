package es.consumo.gescom.modules.autonomousCommunityCountry.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.entity.AutonomousCommunityCountryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutonomousCommunityCountryRepository extends GESCOMRepository<AutonomousCommunityCountryEntity, Long> {

    @Query(value = "SELECT h FROM AutonomousCommunityCountryEntity h  where h.id = :id ")
    Page<AutonomousCommunityCountryEntity.SimpleProjection> findAllAutonomousCommunityCountryByName( Pageable pageable, @Param("id") Long id);

    @Query(value = "SELECT h FROM AutonomousCommunityCountryEntity h  where h.id = :id ")
    AutonomousCommunityCountryEntity findCCAAById( @Param("id") Long id);

    @Query(value = "SELECT h FROM AutonomousCommunityCountryEntity h  where h.code = :code ")
    AutonomousCommunityCountryEntity findByCode( @Param("code") String code);
}
