package es.consumo.gescom.commons.controller;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.commons.db.repository.ReadOnlyRepository;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.IdModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Controlador abstracto que permite realizar las operaciones de lectura de los datos de la aplicación.
 * <p>
 * Este controlador está limitado a ejecutar solamente las operaciones de lectura, por ello está limitado a utilizar
 * un ReadOnlyRepository.
 * <p>
 * Ej. de implementación:
 * <p>
 * <code>
 * {@literal @}RestController<br>
 * {@literal @}RequestMapping("ruta-del-recurso")<br>
 * {@literal @}Tag(name = "título-para-swagger", description = "descripción-para-swagger")<br>
 * public class ExampleController extends AbstractReadController<Entity, ID, FilterCriteria> {
 * ...
 * }
 * </code>
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractReadOnlyController<E extends IdModel, I, F extends FilterCriteria> {
    protected final ReadOnlyRepository<E, I> repository;

    protected AbstractReadOnlyController(ReadOnlyRepository<E, I> repository) {
        this.repository = repository;
    }

    @GetMapping("")
    @Operation(summary = "List all available resources")
    public ResponseEntity<Object> findAll(@ParameterObject @Valid F criteria) {
        return ResponseEntity.ok(performFindAll(criteria));
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a resource by ID")
    public ResponseEntity<Object> findById(@PathVariable I id) {
        Optional<?> result = performFindById(id);
        return result.isPresent()
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }

    @SuppressWarnings("unchecked")
    protected Page<?> performFindAll(F criteria) {
        if (repository instanceof QueryByCriteria) {
            return ((QueryByCriteria<E, F>) repository).findAllByCriteria(criteria, criteria.toPageable());
        }
        return repository.findAll(criteria.toPageable());
    }

    protected Optional<E> performFindById(I id) {
        return repository.findById(id);
    }

}
