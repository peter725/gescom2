package es.consumo.gescom.modules.role.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.role.model.entity.RoleHasModuleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleHasModuleRepository extends GESCOMRepository<RoleHasModuleEntity, Long> {
    @Query("select re from RoleHasModuleEntity re " +
            "join re.permission p " +
            "join re.module m " +
            "where re.roleId=:roleId and p.visible=true and m.visible=true")
    List<RoleHasModuleEntity> findByRoleId(Long roleId);
    
    @Query("select re from RoleHasModuleEntity re " +
            "join re.permission p " +
            "join re.module m " +
            "where re.roleId=:roleId and p.id=:permissionId and m.id=:moduleId and p.visible=true and m.visible=true")
    Optional<RoleHasModuleEntity> findByRoleIdAndPermissionIdAndModuleId(Long roleId, Long permissionId, Long moduleId);
    
    @Query("select re.id from RoleHasModuleEntity re " +
            "join re.permission p " +
            "join re.module m " +
            "where re.roleId=:roleId and m.id=:moduleId and p.id not in (:permissionId) and p.visible=true and m.visible=true")
    List<Long> findIdByRoleIdAndModuleIdAndPermissionIdNotIn(Long roleId, List<Long> permissionId, Long moduleId);
    
    @Query("select re.id from RoleHasModuleEntity re " +
            "join re.permission p " +
            "join re.module m " +
            "where re.roleId=:roleId and m.id not in (:moduleId) and p.visible=true and m.visible=true")
    List<Long> findIdByRoleIdAndModuleIdNotIn(Long roleId, List<Long> moduleId);

    @Query("select m.code as moduleCode , p.code as permissionCode from LoginEntity l  " +
            "JOIN l.roles r " +
            "JOIN RoleHasModuleEntity rm on r.id=rm.roleId " +
            "JOIN ModuleEntity m on rm.module.id=m.id " +
            "JOIN PermissionEntity p on rm.permission.id=p.id " +
            "WHERE l.id= :loginId")
    Set<RoleHasModuleEntity.FullAuthoritiesProjection> findAllByLoginId(Long loginId);

    @Query("select r.name as roleName from LoginEntity l " +
       "JOIN l.roles r " +
       "WHERE l.id= :loginId")
    Set<RoleHasModuleEntity.FullAuthoritiesProjection> findAllRolesByLoginId(@Param("loginId") Long loginId);

}
