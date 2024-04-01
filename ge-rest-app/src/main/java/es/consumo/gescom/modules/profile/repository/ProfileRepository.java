package es.consumo.gescom.modules.profile.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.profile.model.criteria.ProfileCriteria;
import es.consumo.gescom.modules.profile.model.entity.ProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.permission.model.criteria.PermissionCriteria;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;

@Repository
public interface ProfileRepository extends GESCOMRepository<ProfileEntity, Long>,
        QueryByCriteria<ProfileEntity.SimpleProjection, ProfileCriteria> {

    @Query(value = "SELECT a FROM ProfileEntity a "
            + "WHERE "
            + "(:#{#criteria.name} is null OR a.name LIKE :#{#criteria.name}) "
           )
    Page<ProfileEntity.SimpleProjection> findAllByCriteria(ProfileCriteria criteria, Pageable pageable);

}
