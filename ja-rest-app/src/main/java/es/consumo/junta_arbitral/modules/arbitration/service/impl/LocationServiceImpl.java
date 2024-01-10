package es.consumo.junta_arbitral.modules.arbitration.service.impl;

import es.consumo.junta_arbitral.modules.arbitration.model.entity.LocationEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author serikat
 */
@Service
public class LocationServiceImpl extends EntityCrudService<LocationEntity, Long> {
    @Autowired
    public LocationServiceImpl(JJAARepository<LocationEntity, Long> repository) {
        super(repository);
    }


}
