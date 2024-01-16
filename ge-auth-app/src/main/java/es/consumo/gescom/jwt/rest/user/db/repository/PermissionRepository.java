package es.consumo.gescom.jwt.rest.user.db.repository;

import es.consumo.gescom.commons.db.repository.ReadOnlyRepository;
import es.consumo.gescom.jwt.rest.user.db.entity.PermissionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionRepository extends ReadOnlyRepository<PermissionEntity, Long> {

    @Query("select p from PermissionEntity p " +
            "JOIN RoleHasModuleEntity rhm on p.id=rhm.permission.id " +
            "WHERE rhm.roleId in :roles and rhm.module.id in :modules")
    List<PermissionEntity> findAllByModuleListAndRoleList(Set<Long> modules, Set<Long> roles);
}
