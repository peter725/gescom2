package es.consumo.gescom.modules.general.repository;

import es.consumo.gescom.modules.general.model.entity.CountryEntity;
import es.consumo.gescom.commons.db.repository.JJAARepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JJAARepository<CountryEntity, Long> {
}
