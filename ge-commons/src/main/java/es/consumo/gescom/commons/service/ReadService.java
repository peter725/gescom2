package es.consumo.gescom.commons.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ReadService<M, I> {

    Page<?> findAll();

    Page<?> findAll(CriteriaWrapper<?> wrapper);

    Optional<M> findById(I id);

    boolean exitsById(I id);

}
