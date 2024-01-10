package es.consumo.junta_arbitral.commons.service;

import es.consumo.junta_arbitral.commons.db.entity.StatefulEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.EntityStatusChange;
import es.consumo.junta_arbitral.commons.constants.EntityState;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic CRUD implementation with basic operations predefined without using a specific data type.
 * This service is intended to be used a base for other more complex or specific implementations and should be easy
 * to override in order to adapt to requirements.
 */
public abstract class BaseCrudService<E, M, I>
        extends BaseReadService<E, M, I>
        implements CrudService<M, I> {

    protected BaseCrudService(
            JJAARepository<E, I> repository,
            DataConverter<E, M> converter) {
        super(repository, converter);
    }

    @Transactional
    @Override
    public M create(M payload) {
        preCreate(payload);
        M result = performCreate(payload);
        postCreate();
        return result;
    }

    @Transactional
    @Override
    public M update(I id, M payload) {
        preUpdate(id, payload);
        M result = performUpdate(id, payload);
        postUpdate();
        return result;
    }

    @Transactional
    @Override
    public M changeStatus(I id, EntityStatusChange payload) {
        preChangeStatus(id, payload);
        E result = performChangeStatus(id, payload);
        postChangeStatus(result);
        return converter.convertToModel(result);
    }

    @Transactional
    @Override
    public void deleteById(I id) {
        E data = repository.findById(id).orElseThrow(NotFoundException::new);
        preDelete();
        performDelete(data);
        postDelete();
    }

    /**
     * Operations to be executed before creating the resource.
     * <p>
     * Default implementation: logs.
     */
    protected void preCreate(M payload) {
        logger.info("Payload {}", payload);
        logger.trace("Creating a new entity");
    }

    /**
     * Operations to be executed after creating the resource.
     * <p>
     * Default implementation: logs.
     */
    protected void postCreate() {
        logger.trace("Creating a new entity");
    }

    /**
     * Specific implementation to create the resource
     */
    protected abstract M performCreate(M payload);

    /**
     * Operations to be executed before updating the resource.
     * <p>
     * Default implementation: logs.
     */
    protected void preUpdate(I id, M payload) {
        logger.info("id {}, Payload {}", id, payload);
        logger.trace("Updating an existing entity");
    }

    /**
     * Operations to be executed after updating the resource.
     * <p>
     * Default implementation: logs.
     */
    protected void postUpdate() {
        logger.trace("Updated an existing entity");
    }

    /**
     * Specific implementation to update the resource
     */
    protected abstract M performUpdate(I id, M payload);

    /**
     * Operations to be executed before changing the resource status.
     * <p>
     * Default implementation: logs.
     */
    protected void preChangeStatus(I id, EntityStatusChange payload) {
    }

    /**
     * Operations to be executed before changing the resource status.
     * <p>
     * Default implementation: logs.
     */
    protected void postChangeStatus(E result) {
    }

    /**
     * Implementation to change the entities' status.
     */
    protected E performChangeStatus(I id, EntityStatusChange payload) {
        E data = repository.findById(id).orElseThrow(NotFoundException::new);
        // Not all entities support a State control. Entities that don't support it are skipped and nothing is changed.
        if (data instanceof StatefulEntity && EntityState.contains(payload.getStatus())) {
            ((StatefulEntity) data).setState(payload.getStatus());
            repository.save(data);
        }
        return data;
    }

    /**
     * Operations to be executed before deleting the resource.
     * <p>
     * Default implementation: logs.
     */
    protected void preDelete() {
        logger.trace("Deleting an entity");
    }

    /**
     * Operations to be executed after updating the resource.
     * <p>
     * Default implementation: logs.
     */
    protected void postDelete() {
        logger.trace("Deleted an entity");
    }

    /**
     * Specific implementation to delete the resource
     */
    protected void performDelete(E data) {
        // Not all entities support a State control. Entities that don't support it are skipped and nothing is changed.
        if (data instanceof StatefulEntity) {
            ((StatefulEntity) data).setState(EntityState.OFF.getValue());
            repository.save(data);
        } else {
            repository.delete(data);
        }
    }
}
