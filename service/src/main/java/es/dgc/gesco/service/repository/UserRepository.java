package es.dgc.gesco.service.repository;


import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends BaseRepository<User, Long>,
        QueryByCriteria<User, UserCriteria> {

    @Override
    @Query(value = """
            SELECT t FROM User t WHERE 
            (:#{#criteria.name} is null or t.name LIKE :#{#criteria.name}) 
            AND (t.state IN (:#{#criteria.state}))
            AND (:#{#criteria.firstSurname} is null OR UPPER(t.firstSurname) LIKE UPPER(:#{#criteria.firstSurname})) 
            AND (:#{#criteria.secondSurname} is null OR UPPER(t.secondSurname) LIKE UPPER(:#{#criteria.secondSurname})) 
            AND (:#{#criteria.nif} is null OR UPPER(t.nif) LIKE UPPER(:#{#criteria.nif})) 
            AND (:#{#criteria.email} is null OR UPPER(t.email) LIKE UPPER(:#{#criteria.email})) 
            AND (
            :#{#criteria.search} IS null 
            OR UPPER(t.name) LIKE UPPER(:#{#criteria.search}) 
            OR UPPER(t.firstSurname) LIKE UPPER(:#{#criteria.search}) 
            OR UPPER(t.secondSurname) LIKE UPPER(:#{#criteria.search}) 
            OR UPPER(t.nif) LIKE UPPER(:#{#criteria.search}) 
            OR UPPER(t.email) LIKE UPPER(:#{#criteria.search}) 
            )
            """
    )
    Page<User> findAllByCriteria(@Param("criteria") UserCriteria criteria, Pageable pageable);

    Optional<User> findByNif (String nif);

    @Override
    List<User> findAll();


    Page<User> findAll(@Param("pageable") Pageable pageable);
}
