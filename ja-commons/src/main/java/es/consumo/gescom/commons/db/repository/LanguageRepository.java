package es.consumo.gescom.commons.db.repository;

import es.consumo.gescom.commons.db.entity.Language;
import es.consumo.gescom.commons.dto.GeneralCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository
        extends JJAARepository<Language, Long>,
        QueryByCriteria<Language, GeneralCriteria> {

    Optional<Language> findByIsoCode(String code);

    @Override
    @Query(value = "SELECT t FROM Language t "
            + "WHERE "
            + "(:#{#criteria.name} is null OR t.name LIKE :#{#criteria.name}) "
            + "AND (COALESCE(:#{#criteria.state}, null) is null OR t.state IN (:#{#criteria.state}))"
            + "AND ("
            + ":#{#criteria.search} IS null "
            + ")"
    )
    Page<Language> findAllByCriteria(GeneralCriteria criteria, Pageable pageable);

}
