package es.consumo.junta_arbitral.commons.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<E, I> extends Repository<E, I> {
    Optional<E> findById(I id);

    List<E> findAll();

    Page<E> findAll(Pageable pageable);
}
