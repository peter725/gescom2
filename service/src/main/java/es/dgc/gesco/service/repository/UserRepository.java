package es.dgc.gesco.service.repository;


import es.dgc.gesco.model.commom.db.repository.BaseRepository;
import es.dgc.gesco.model.commom.db.repository.QueryByCriteria;
import es.dgc.gesco.model.modules.user.db.entity.Users;
import es.dgc.gesco.model.modules.user.dto.criteria.UsersCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends BaseRepository<Users, Long>,
        QueryByCriteria<Users, UsersCriteria> {

    @Override
    @Query(value = "SELECT t FROM Users t WHERE "
            + "(:#{#criteria.name} is null or t.name LIKE :#{#criteria.name}) "
            + "AND (COALESCE(:#{#criteria.state}, null) is null OR t.state IN (:#{#criteria.state})) "
            + "AND (:#{#criteria.firstSurname} is null OR UPPER(t.firstSurname) LIKE UPPER(:#{#criteria.firstSurname})) "
            + "AND (:#{#criteria.secondSurname} is null OR UPPER(t.secondSurname) LIKE UPPER(:#{#criteria.secondSurname})) "
            + "AND (:#{#criteria.email} is null OR UPPER(t.email) LIKE UPPER(:#{#criteria.email})) "
            + "AND ("
            + ":#{#criteria.search} IS null "
            + "OR UPPER(t.name) LIKE UPPER(:#{#criteria.search}) "
            + "OR UPPER(t.firstSurname) LIKE UPPER(:#{#criteria.search}) "
            + "OR UPPER(t.secondSurname) LIKE UPPER(:#{#criteria.search}) "
            + "OR UPPER(t.email) LIKE UPPER(:#{#criteria.search}) "
            + ")"
    )
    Page<Users> findAllByCriteria(@Param("criteria") UsersCriteria criteria, Pageable pageable);


}
