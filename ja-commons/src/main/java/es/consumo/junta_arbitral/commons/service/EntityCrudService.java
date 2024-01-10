package es.consumo.junta_arbitral.commons.service;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.IdModel;
import es.consumo.junta_arbitral.commons.converter.SameDataConverter;

/**
 * Specialized CRUD service designed to only work with entities and does not require a converter.
 * <p>
 * Should be useful for most simple entities that require CRUD operations.
 */
public abstract class EntityCrudService<E extends IdModel<I>, I>
        extends AbstractCrudService<E, E, I> {

    protected EntityCrudService(JJAARepository<E, I> repository) {
        super(repository, SameDataConverter.getInstance());
    }
}
