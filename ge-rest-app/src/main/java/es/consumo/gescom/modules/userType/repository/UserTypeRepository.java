package es.consumo.gescom.modules.userType.repository;


import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.userType.model.criteria.UserTypeCriteria;
import es.consumo.gescom.modules.userType.model.entity.UserTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends GESCOMRepository<UserTypeEntity, Long>,
        QueryByCriteria<UserTypeEntity.SimpleProjection, UserTypeCriteria> {

    @Query(value = "SELECT a FROM UserTypeEntity a "
            + "WHERE "
            + "(:#{#criteria.name} is null OR a.name LIKE :#{#criteria.name}) "
           )
    Page<UserTypeEntity.SimpleProjection> findAllByCriteria(UserTypeCriteria criteria, Pageable pageable);

}
