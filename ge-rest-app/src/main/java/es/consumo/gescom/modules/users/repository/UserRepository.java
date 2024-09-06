package es.consumo.gescom.modules.users.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.users.model.entity.UserEntity;
import es.consumo.gescom.modules.users.model.criteria.UserCriteria;

@Repository
public interface UserRepository extends GESCOMRepository<UserEntity, Long> , QueryByCriteria<UserEntity.SimpleProjection, UserCriteria> {
    
        @Query(value = "SELECT a FROM UserEntity a "
        + "WHERE "
        + "(:#{#criteria.name} is null OR UPPER(a.name) LIKE :#{#criteria.name}) "
        + "AND (:#{#criteria.surname} is null OR UPPER(a.surname) LIKE :#{#criteria.surname}) "
        + "AND (:#{#criteria.lastSurname} is null OR UPPER(a.lastSurname) LIKE :#{#criteria.lastSurname}) "
        + "AND (:#{#criteria.dni} is null OR UPPER(a.dni) LIKE :#{#criteria.dni}) "
        + "AND (:#{#criteria.email} is null OR UPPER(a.email) LIKE :#{#criteria.email}) "
        + "AND (:#{#criteria.phone} is null OR UPPER(a.phone) LIKE :#{#criteria.phone}) "
        + "AND (:#{#criteria.state} is null OR a.state in :#{#criteria.state}) "
        + "AND ("
        + ":#{#criteria.search} is null "
        + "OR UPPER(a.name) like :#{#criteria.search} "
        + "OR UPPER(a.surname) LIKE :#{#criteria.search} "
        + "OR UPPER(a.lastSurname) LIKE :#{#criteria.search} "
        + "OR UPPER(a.dni) LIKE :#{#criteria.search} "
        + "OR UPPER(a.email) LIKE :#{#criteria.search} "
        + "OR UPPER(a.phone) LIKE :#{#criteria.search} "
        + "OR UPPER(a.role.name) LIKE :#{#criteria.search} )"
        )
        public Page<UserEntity.SimpleProjection> findAllByCriteria(UserCriteria criteria, Pageable pageable);


}