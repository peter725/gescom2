package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.commom.dto.FilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface QueryByCriteria<E, F extends FilterCriteria> {
    /**
     * This method must be overwritten and implemented using @Query in order for it to work.
     * <p>
     * Example implementation:
     * <pre>
     * {@literal @}Override
     * {@literal @}Query(value = "MY-CUSTOM-SELECT")
     * Page<Entity> findAllByCriteria(@Param("criteria") FilterCriteria criteria, Pageable pageable)
     * </pre>
     * <p>
     * Select syntax example to work with FC:
     * <pre>
     * SELECT t FROM Entity t WHERE
     * (:#{#criteria.simpleField} is null or t.simpleField LIKE :#{#criteria.simpleField})
     * AND (COALESCE(:#{#criteria.listField}, null) is null OR t.listField IN (:#{#criteria.listField}))
     * </pre>
     * <p>
     * Common queries examples:
     * - Limitar los valores por idioma
     * <pre>(:#{#criteria.languageIsoCode} is null OR UPPER(t.languageIsoCode) LIKE UPPER(:#{#criteria.languageIsoCode}))</pre>
     * - Limitar por estados de la entidad
     * <pre>(COALESCE(:#{#criteria.state}, null) is null OR t.state IN (:#{#criteria.state}))</pre>
     * - Limitar el scope de los datos (atención wildcard)
     * <pre>(:#{#criteria.scope} is null OR t.scope LIKE :#{#criteria.scope}%)</pre>
     */
    @Query(value = "SELECT DUMMY FROM DUAL D", nativeQuery = true)
    Page<E> findAllByCriteria(@Param("criteria") F criteria, Pageable pageable);


}
