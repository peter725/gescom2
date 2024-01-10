package es.consumo.junta_arbitral.commons.service;

import es.consumo.junta_arbitral.commons.dto.EntityStatusChange;

public interface CrudService<M, I> extends ReadService<M, I> {

    M create(M payload);

    M update(I id, M payload);

    M changeStatus(I id, EntityStatusChange payload);

    void deleteById(I id);

}
