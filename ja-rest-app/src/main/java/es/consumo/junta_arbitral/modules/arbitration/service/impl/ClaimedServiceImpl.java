package es.consumo.junta_arbitral.modules.arbitration.service.impl;

import es.consumo.junta_arbitral.modules.arbitration.model.entity.ClaimedEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.LocationEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author serikat
 */
@Service
public class ClaimedServiceImpl extends EntityCrudService<ClaimedEntity, Long> {
    private final CrudService<LocationEntity, Long> locationService;

    @Autowired
    public ClaimedServiceImpl(JJAARepository<ClaimedEntity, Long> repository,
                              CrudService<LocationEntity, Long> locationService) {
        super(repository);
        this.locationService = locationService;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ClaimedEntity performCreate(ClaimedEntity payload) {
        LocationEntity location = locationService.create(payload.getLocation());
        payload.setLocation(location);
        return repository.save(payload);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ClaimedEntity performUpdate(Long id, ClaimedEntity payload) {
        locationService.update(payload.getLocation().getId(), payload.getLocation());
        return repository.save(payload);
    }
}
