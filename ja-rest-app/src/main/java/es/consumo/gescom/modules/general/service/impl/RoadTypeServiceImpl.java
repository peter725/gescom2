package es.consumo.gescom.modules.general.service.impl;

import es.consumo.gescom.modules.general.model.entity.RoadTypeEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author serikat
 */
@Service
public class RoadTypeServiceImpl extends EntityReadService<RoadTypeEntity, Long> {
    @Autowired
    public RoadTypeServiceImpl(GESCOMRepository<RoadTypeEntity, Long> repository) {
        super(repository);
    }
}
