package es.consumo.gescom.commons.controller;

import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.ReadService;
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
public abstract class AbstractReadController<S extends ReadService<E, I>, E, I, F extends FilterCriteria> {

    protected final S service;

    public AbstractReadController(S service) {
        this.service = service;
    }

    @GetMapping("")
//	@Operation(summary = "List all available resources")
    public ResponseEntity<Object> findAll(@ParameterObject @Valid F criteria) {
        return ResponseEntity.ok(performFindAll(new CriteriaWrapper<>(criteria)));
    }

    @GetMapping("{id}")
//	@Operation(summary = "Get a resource by ID")
    public ResponseEntity<Object> findById(@PathVariable I id) {
        Optional<?> result = performFindById(id);
        return result.isPresent()
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }

    protected Page<?> performFindAll(CriteriaWrapper<?> criteriaWrapper) {
        return service.findAll(criteriaWrapper);
    }

    protected Optional<?> performFindById(I id) {
        return service.findById(id);
    }
}
