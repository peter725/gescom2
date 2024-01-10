package es.consumo.junta_arbitral.commons.controller;

import es.consumo.junta_arbitral.commons.db.entity.BaseEntity;
import es.consumo.junta_arbitral.commons.dto.EntityStatusChange;
import es.consumo.junta_arbitral.commons.dto.FilterCriteria;
import es.consumo.junta_arbitral.commons.service.CrudService;

/**
 * Abstract CRUD controller specialized to only work with application entities. No conversion is required.
 * <p>
 * Implementation example:
 * <p>
 * <code>
 * {@literal @}RestController<br>
 * {@literal @}RequestMapping("ApiEndpoints + path-to-resource")<br>
 * {@literal @}Tag(name = "swagger title", description = "swagger description")<br>
 * public class ExampleController extends AbstractRestController<Entity, ID, FilterCriteria> {
 * ...
 * }
 * </code>
 */
public abstract class EntityCrudController<E extends BaseEntity, I, F extends FilterCriteria>
        extends BaseCrudController<E, E, I, F> {

    protected EntityCrudController(CrudService<E, I> service) {
        super(service);
    }

    @Override
    protected E performCreate(E payload) {
        return service.create(payload);
    }

    @Override
    protected E performUpdate(I id, E payload) {
        return service.update(id, payload);
    }

    @Override
    protected E performChangeState(I id, EntityStatusChange payload) {
        return service.changeStatus(id, payload);
    }
}
