package es.consumo.gescom.modules.general.repository;

import es.consumo.gescom.modules.general.model.entity.ProvinceEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends GESCOMRepository<ProvinceEntity, Long> {
}
