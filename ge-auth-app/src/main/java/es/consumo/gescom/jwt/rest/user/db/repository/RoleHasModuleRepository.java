package es.consumo.gescom.jwt.rest.user.db.repository;

import es.consumo.gescom.commons.db.repository.ReadOnlyRepository;
import es.consumo.gescom.jwt.rest.user.db.entity.RoleHasModuleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleHasModuleRepository extends ReadOnlyRepository<RoleHasModuleEntity, Long> {

    @Query("select m.code as moduleCode , p.code as permissionCode from LoginEntity l  " +
            "JOIN l.roles r " +
            "JOIN RoleHasModuleEntity rm on r.id=rm.roleId " +
            "JOIN ModuleEntity m on rm.module.id=m.id " +
            "JOIN PermissionEntity p on rm.permission.id=p.id " +
            "WHERE l.id= :loginId")
    Set<RoleHasModuleEntity.FullAuthoritiesProjection> findAllByLoginId(Long loginId);
}
