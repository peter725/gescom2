package es.consumo.gescom.commons.service;

import es.consumo.gescom.commons.dto.EntityStatusChange;

public interface CrudService<M, I> extends ReadService<M, I> {

    M create(M payload);

    M update(I id, M payload);

    M changeStatus(I id, EntityStatusChange payload);

    void deleteById(I id);

}
