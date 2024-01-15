package es.consumo.gescom.commons.service;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.commons.dto.IdModel;
import es.consumo.gescom.commons.converter.DataConverter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic CRUD implementation for BaseEntities. This is a specific implementation of CRUD service that is intended
 * to be used with simple entities that don't require complex operations.
 * <p>
 * Should be useful for most entities.
 */
public abstract class AbstractCrudService<E extends IdModel<I>, M, I>
        extends BaseCrudService<E, M, I> {

    protected AbstractCrudService(
            JJAARepository<E, I> repository,
            DataConverter<E, M> converter) {
        super(repository, converter);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    protected M performCreate(M payload) {
        E source = converter.convertToEntity(payload);
        E result = repository.save(source);
        return converter.convertToModel(result);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    protected M performUpdate(I id, M payload) {
        E source = converter.convertToEntity(payload);
        E result = repository.save(source);
        return converter.convertToModel(result);
    }

}
