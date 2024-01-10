package es.consumo.junta_arbitral.modules.general.service.impl;

import es.consumo.junta_arbitral.modules.general.model.entity.CountryEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.service.EntityReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author serikat
 */
@Service
public class CountryServiceImpl extends EntityReadService<CountryEntity, Long> {
    @Autowired
    public CountryServiceImpl(JJAARepository<CountryEntity, Long> repository) {
        super(repository);
    }
}
