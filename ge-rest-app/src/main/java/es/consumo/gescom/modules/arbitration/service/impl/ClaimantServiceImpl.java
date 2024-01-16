package es.consumo.gescom.modules.arbitration.service.impl;

import es.consumo.gescom.modules.arbitration.model.constants.ClaimantType;
import es.consumo.gescom.modules.arbitration.model.entity.ClaimantEntity;
import es.consumo.gescom.modules.arbitration.model.entity.LocationEntity;
import es.consumo.gescom.modules.arbitration.model.entity.RepresentedEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.commons.service.EntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author serikat
 */
@Service
public class ClaimantServiceImpl extends EntityCrudService<ClaimantEntity, Long> {
    private final CrudService<LocationEntity, Long> locationService;
    private final CrudService<RepresentedEntity, Long> representedService;

    @Autowired
    public ClaimantServiceImpl(GESCOMRepository<ClaimantEntity, Long> repository,
                               CrudService<LocationEntity, Long> locationService,
                               CrudService<RepresentedEntity, Long> representedService) {
        super(repository);
        this.locationService = locationService;
        this.representedService = representedService;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ClaimantEntity performCreate(ClaimantEntity payload) {
        LocationEntity location = locationService.create(payload.getLocation());

        payload.setLocation(location);
        RepresentedEntity temp = payload.getRepresented();
        payload.setRepresented(null);
        ClaimantEntity entity = repository.saveAndFlush(payload);
        createRepresented(entity, temp);
        return entity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ClaimantEntity performUpdate(Long id, ClaimantEntity payload) {
        locationService.update(payload.getLocation().getId(), payload.getLocation());
        updateRepresented(payload);
        return repository.save(payload);
    }

    private void createRepresented(ClaimantEntity entity, RepresentedEntity represented) {
        if (Objects.nonNull(entity.getClaimantType()) && entity.getClaimantType().equals(ClaimantType.THIRD)) {
            represented.setId(entity.getId());
            entity.setRepresented(representedService.create(represented));
        } else {
            entity.setRepresented(null);
        }
    }

    private void updateRepresented(ClaimantEntity entity) {

        if (Objects.nonNull(entity.getClaimantType()) && entity.getClaimantType().equals(ClaimantType.THIRD)
                && (Objects.isNull(entity.getRepresented()) || Objects.nonNull(entity.getRepresented().getId()))) {
            entity.getRepresented().setId(entity.getId());
            entity.setRepresented(representedService.update(entity.getId(), entity.getRepresented()));
        } else {
            createRepresented(entity, entity.getRepresented());
        }
    }
}
