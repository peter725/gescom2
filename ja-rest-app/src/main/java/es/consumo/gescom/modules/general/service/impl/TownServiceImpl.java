package es.consumo.gescom.modules.general.service.impl;

import es.consumo.gescom.modules.general.model.entity.TownEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author serikat
 */
@Service
public class TownServiceImpl extends EntityReadService<TownEntity, Long> {
    @Autowired
    public TownServiceImpl(GESCOMRepository<TownEntity, Long> repository) {
        super(repository);
    }
}
