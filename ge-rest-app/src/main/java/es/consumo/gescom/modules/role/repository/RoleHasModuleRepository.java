package es.consumo.gescom.modules.role.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.role.model.entity.RoleHasModuleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleHasModuleRepository extends GESCOMRepository<RoleHasModuleEntity, Long> {
    @Query("select re from RoleHasModuleEntity re " +
            "join re.permission p " +
            "join re.module m " +
            "where re.roleId=:roleId and p.visible=true and m.visible=true")
    List<RoleHasModuleEntity> findByRoleId(Long roleId);

    @Query("select m.code as moduleCode , p.code as permissionCode from LoginEntity l  " +
            "JOIN l.roles r " +
            "JOIN RoleHasModuleEntity rm on r.id=rm.roleId " +
            "JOIN ModuleEntity m on rm.module.id=m.id " +
            "JOIN PermissionEntity p on rm.permission.id=p.id " +
            "WHERE l.id= :loginId")
    Set<RoleHasModuleEntity.FullAuthoritiesProjection> findAllByLoginId(Long loginId);
}
