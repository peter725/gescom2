package es.consumo.gescom.commons.controller;

import es.consumo.gescom.commons.dto.EntityStatusChange;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.commons.validation.group.ChangeStatus;
import es.consumo.gescom.commons.validation.group.ResourceCreate;
import es.consumo.gescom.commons.validation.group.ResourceDelete;
import es.consumo.gescom.commons.validation.group.ResourceUpdate;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Base implementation
 */
public abstract class BaseCrudController<E, M, I, F extends FilterCriteria>
        extends AbstractReadController<CrudService<E,I>,E, I, F>
        implements CrudController<M, I, F> {

    protected BaseCrudController(CrudService<E, I> service) {
        super(service);
    }


    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new resource")
    @Validated(ResourceCreate.class)
    public ResponseEntity<Object> create(@Valid @RequestBody M payload) {
        preCreate(payload);
        M entity = performCreate(payload);
        postCreate(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PostMapping(value = "update/{id}")
    @Operation(summary = "Update an existing resource")
    @Validated(ResourceUpdate.class)
    public ResponseEntity<Object> update(@PathVariable I id, @Valid @RequestBody M payload) {
        preUpdate(id, payload);
        M result = performUpdate(id, payload);
        postUpdate(result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("patch/{id}/status")
    @Operation(summary = "Patch an existing resource status")
    @Validated(ChangeStatus.class)
    public ResponseEntity<Object> changeState(@PathVariable I id, @Valid @RequestBody EntityStatusChange payload) {
        return ResponseEntity.ok(performChangeState(id, payload));
    }

    @PostMapping("delete/{id}")
    @Operation(summary = "Delete an existing resource")
    @Validated(ResourceDelete.class)
    public ResponseEntity<Object> delete(@PathVariable I id) {
        // validate data for delete constraint
        preDelete(id);
        performDelete(id);
        postDelete(id);
        return ResponseEntity.noContent().build();
    }


    protected void preCreate(M payload) {
    }

    protected abstract M performCreate(M payload);

    protected void postCreate(M result) {
    }

    protected void preUpdate(I id, M payload) {
    }

    protected abstract M performUpdate(I id, M payload);

    protected void postUpdate(M result) {
    }

    protected abstract M performChangeState(I id, EntityStatusChange payload);

    protected void preDelete(I id) {
    }

    protected void performDelete(I id) {
        ((CrudService<E, I>) service).deleteById(id);
    }

    protected void postDelete(I id) {
    }

}
