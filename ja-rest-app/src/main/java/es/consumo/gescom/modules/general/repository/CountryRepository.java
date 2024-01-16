package es.consumo.gescom.modules.general.repository;

import es.consumo.gescom.modules.general.model.entity.CountryEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends GESCOMRepository<CountryEntity, Long> {
}
