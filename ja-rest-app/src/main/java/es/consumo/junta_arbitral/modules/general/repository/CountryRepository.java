package es.consumo.junta_arbitral.modules.general.repository;

import es.consumo.junta_arbitral.modules.general.model.entity.CountryEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JJAARepository<CountryEntity, Long> {
}
