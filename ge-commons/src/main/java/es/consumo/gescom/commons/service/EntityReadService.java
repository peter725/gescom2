package es.consumo.gescom.commons.service;

import es.consumo.gescom.commons.db.entity.BaseEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.converter.SameDataConverter;

/**
 * Specialized READ service designed to only work with entities and does not require a converter.
 * <p>
 * Should be useful for most simple entities.
 */
public abstract class EntityReadService<E extends BaseEntity, I>
        extends BaseReadService<E, E, I> {

    protected EntityReadService(
            GESCOMRepository<E, I> repository) {
        super(repository, SameDataConverter.getInstance());
    }
}
