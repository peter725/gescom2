package es.consumo.gescom.modules.general.service.impl;

import es.consumo.gescom.modules.general.model.entity.CountryEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author serikat
 */
@Service
public class CountryServiceImpl extends EntityReadService<CountryEntity, Long> {
    @Autowired
    public CountryServiceImpl(GESCOMRepository<CountryEntity, Long> repository) {
        super(repository);
    }
}
