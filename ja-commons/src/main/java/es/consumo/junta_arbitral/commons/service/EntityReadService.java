package es.consumo.junta_arbitral.commons.service;

import es.consumo.junta_arbitral.commons.db.entity.BaseEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.converter.SameDataConverter;

/**
 * Specialized READ service designed to only work with entities and does not require a converter.
 * <p>
 * Should be useful for most simple entities.
 */
public abstract class EntityReadService<E extends BaseEntity, I>
        extends BaseReadService<E, E, I> {

    protected EntityReadService(
            JJAARepository<E, I> repository) {
        super(repository, SameDataConverter.getInstance());
    }
}
