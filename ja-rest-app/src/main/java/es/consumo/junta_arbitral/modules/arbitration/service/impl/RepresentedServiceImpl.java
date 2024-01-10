package es.consumo.junta_arbitral.modules.arbitration.service.impl;

import es.consumo.junta_arbitral.modules.arbitration.model.entity.LocationEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.RepresentedEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.service.CrudService;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author serikat
 */
@Service
public class RepresentedServiceImpl extends EntityCrudService<RepresentedEntity, Long> {
    private final CrudService<LocationEntity, Long> locationService;

    @Autowired
    public RepresentedServiceImpl(JJAARepository<RepresentedEntity, Long> repository,
                                  CrudService<LocationEntity, Long> locationService) {
        super(repository);
        this.locationService = locationService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RepresentedEntity create(RepresentedEntity payload) {
        LocationEntity location = locationService.create(payload.getLocation());
        payload.setLocation(location);
        payload.setClaimant(null);
        return repository.save(payload);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RepresentedEntity performUpdate(Long id, RepresentedEntity entity) {
        /**
         * SE valida debido a que el beck del front puede borrar el id del registro
         */
        if (Objects.isNull(entity.getLocation().getId())) {
            long locationId = findById(id).orElseThrow().getLocation().getId();
            entity.getLocation().setId(locationId);
        }
        entity.setLocation(locationService.update(entity.getLocation().getId(), entity.getLocation()));
        entity.setClaimant(null);
        return repository.save(entity);
    }

}
