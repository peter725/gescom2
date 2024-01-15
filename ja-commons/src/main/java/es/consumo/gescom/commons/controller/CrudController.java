package es.consumo.gescom.commons.controller;

import es.consumo.gescom.commons.dto.EntityStatusChange;
import org.springframework.http.ResponseEntity;

public interface CrudController<M, I, F> {

    /**
     * Lists all the resources for the provided filters.
     */
    ResponseEntity<Object> findAll(F criteria);

    /**
     * Finds a specific resource by ID.
     */
    ResponseEntity<Object> findById(I id);

    /**
     * Creates a new resource.
     */
    ResponseEntity<Object> create(M payload);

    /**
     * Updates an existing resource.
     */
    ResponseEntity<Object> update(I id, M payload);

    /**
     * Changes the state of an existing resource.
     */
    ResponseEntity<Object> changeState(I id, EntityStatusChange payload);

    /**
     * Deletes an existing resource.
     */
    ResponseEntity<Object> delete(I id);

}
