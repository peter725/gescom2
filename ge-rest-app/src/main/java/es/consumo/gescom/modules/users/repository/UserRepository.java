package es.consumo.gescom.modules.users.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.users.model.entity.UserEntity;
import es.consumo.gescom.modules.users.model.criteria.UserCriteria;

@Repository
public interface UserRepository extends GESCOMRepository<UserEntity, Long> , QueryByCriteria<UserEntity.SimpleProjection, UserCriteria> {
    
        @Query(value = "SELECT a FROM UserEntity a "
        + "WHERE "
        + "(:#{#criteria.name} is null OR a.name LIKE :#{#criteria.name}) "
        + "AND (:#{#criteria.surname} is null OR a.surname LIKE :#{#criteria.surname}) "
        + "AND (:#{#criteria.lastSurname} is null OR a.lastSurname LIKE :#{#criteria.lastSurname}) "
        + "AND (:#{#criteria.dni} is null OR a.dni LIKE :#{#criteria.dni}) "
        + "AND (:#{#criteria.createdAtLTE} is null OR a.createAt <= :#{#criteria.createdAtLTEConvert}) "
        + "AND (:#{#criteria.createdAtGTE} is null OR a.createAt >= :#{#criteria.createdAtGTEConvert}) "
        + "AND (:#{#criteria.updatedAtLTE} is null OR a.updateAt <= :#{#criteria.updatedAtLTEConvert}) "
        + "AND (:#{#criteria.updatedAtGTE} is null OR a.updateAt >= :#{#criteria.updatedAtGTEConvert}) "
        + "AND (:#{#criteria.search} is null OR a.name like :#{#criteria.search} OR a.surname like :#{#criteria.search} OR a.dni like :#{#criteria.search} "
        + "OR a.lastSurname like :#{#criteria.search})"
    )
    Page<UserEntity.SimpleProjection> findAllByCriteria(UserCriteria criteria, Pageable pageable);


}