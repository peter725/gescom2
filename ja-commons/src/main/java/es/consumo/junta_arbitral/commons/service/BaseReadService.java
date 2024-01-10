package es.consumo.junta_arbitral.commons.service;

import es.consumo.junta_arbitral.commons.db.repository.QueryByCriteria;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.FilterCriteria;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.converter.DataConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Generic Service, easy to extend, that is limited only to read operations by default. Extensions of this service may
 * include non read operations.
 */
public abstract class BaseReadService<E, M, I> implements ReadService<M, I> {

    protected final JJAARepository<E, I> repository;
    protected final DataConverter<E, M> converter;
    protected final Logger logger;

    protected BaseReadService(
            JJAARepository<E, I> repository,
            DataConverter<E, M> converter) {
        this.repository = repository;
        this.converter = converter;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * Returns an unpaged result of all the existing resources. Since it's unpaged, it may cause performance
     * issues depending on the accessed resource.
     */
    @Override
    public Page<?> findAll() {
        return findAll(new CriteriaWrapper<>(FilterCriteria.unpaged()));
    }

    /**
     * Finds all values in the repository using the provided criteria.
     * <p>
     * In order for the FilterCriteria to work, the provided repository must extend {@link QueryByCriteria}. If the
     * condition is not met, the result will only use the pagination configuration.
     */
    @Override
    public Page<?> findAll(CriteriaWrapper<?> wrapper) {
        FilterCriteria criteria = wrapper.getCriteria();
        if (repository instanceof QueryByCriteria) {
            return findAllFromCriteria(criteria);
        }
        return findAllFromPageable(criteria.toPageable());
    }

    /**
     * Finds a resource by ID.
     * <p>
     * Returns null if none was found.
     */
    @Override
    public Optional<M> findById(I id) {
        return repository.findById(id).map(converter::convertToModel);
    }

    @Override
    public boolean exitsById(I id) {
        return repository.existsById(id);
    }

    @SuppressWarnings("unchecked")
    protected <F>Page<F> findAllFromCriteria(FilterCriteria criteria) {
        // Find an alternative way to apply this query
        QueryByCriteria<E, FilterCriteria> r = (QueryByCriteria<E, FilterCriteria>) repository;
        return (Page<F>) r.findAllByCriteria(criteria, criteria.toPageable());
    }

    protected Page<?> findAllFromPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
