package es.consumo.junta_arbitral.commons.controller;

import es.consumo.junta_arbitral.commons.converter.DataConverter;
import es.consumo.junta_arbitral.commons.db.entity.BaseEntity;
import es.consumo.junta_arbitral.commons.dto.EntityStatusChange;
import es.consumo.junta_arbitral.commons.dto.FilterCriteria;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Abstract CRUD controller designed to receive an incoming model, transform it and send to
 * <p>
 * Implementation example:
 * <p>
 * <code>
 * {@literal @}RestController<br>
 * {@literal @}RequestMapping("ruta-del-recurso")<br>
 * {@literal @}Tag(name = "título-para-swagger", description = "descripción-para-swagger")<br>
 * public class ExampleController extends AbstractRestController<Entity, ID, FilterCriteria> {
 * ...
 * }
 * </code>
 */
public abstract class AbstractCrudController<E extends BaseEntity, M, I, F extends FilterCriteria>
        extends BaseCrudController<E, M, I, F>
        implements CrudController<M, I, F> {

    private final DataConverter<E, M> dataConverter;

    protected AbstractCrudController(
            CrudService<E, I> service,
            DataConverter<E, M> dataConverter
    ) {
        super(service);
        this.dataConverter = dataConverter;
    }

    @Override
    protected Page<?> performFindAll(CriteriaWrapper<?> criteriaWrapper) {
        return service.findAll(criteriaWrapper);
    }

    @Override
    protected Optional<?> performFindById(I id) {
        return service.findById(id).map(dataConverter::convertToModel);
    }

    @Override
    protected M performCreate(M payload) {
        E src = dataConverter.convertToEntity(payload);
        E result = service.create(src);
        return dataConverter.convertToModel(result);
    }

    @Override
    protected M performUpdate(I id, M payload) {
        E result = service.findById(id).orElseThrow();
        dataConverter.mergeToEntity(result, payload);
        service.update(id, result);
        return dataConverter.convertToModel(result);
    }

    @Override
    protected M performChangeState(I id, EntityStatusChange payload) {
        return dataConverter.convertToModel(service.changeStatus(id, payload));
    }

    protected <T> Page<T> getPage(ModelMapper modelMapper, Page<?> result, Class<T> targetClass) {
        List<T> mapList = map(modelMapper, result, targetClass);
        return new PageImpl<>(mapList, result.getPageable(), result.getTotalElements());
    }

    private <T> List<T> map(ModelMapper modelMapper, Page<?> objects, Class<T> targetClass) {
        return objects.getContent().stream().map(e -> modelMapper.map(e, targetClass)).collect(Collectors.toList());
    }
}
