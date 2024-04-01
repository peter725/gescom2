package es.consumo.gescom.modules.country.repository;

import es.consumo.gescom.modules.country.model.entity.CountryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

@Repository
public interface CountryRepository extends GESCOMRepository<CountryEntity, Long> {

    @Query(value = "SELECT h FROM CountryEntity h where h.id = :id ")
    Page<CountryEntity.SimpleProjection> findAllCountryById(Pageable pageable, @Param("id") Long id);

}
