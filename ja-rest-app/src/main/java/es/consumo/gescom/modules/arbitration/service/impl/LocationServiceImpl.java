package es.consumo.gescom.modules.arbitration.service.impl;

import es.consumo.gescom.modules.arbitration.model.entity.LocationEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author serikat
 */
@Service
public class LocationServiceImpl extends EntityCrudService<LocationEntity, Long> {
    @Autowired
    public LocationServiceImpl(GESCOMRepository<LocationEntity, Long> repository) {
        super(repository);
    }


}
