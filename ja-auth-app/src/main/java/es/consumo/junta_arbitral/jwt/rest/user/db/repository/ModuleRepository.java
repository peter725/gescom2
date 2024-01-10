package es.consumo.junta_arbitral.jwt.rest.user.db.repository;

import es.consumo.junta_arbitral.commons.db.repository.ReadOnlyRepository;
import es.consumo.junta_arbitral.jwt.rest.user.db.entity.ModuleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ModuleRepository extends ReadOnlyRepository<ModuleEntity, Long> {
    @Query("select m from ModuleEntity m " +
            "join RoleHasModuleEntity rhm on m.id=rhm.module.id " +
            "join RoleEntity r on rhm.roleId=r.id join r.logins l " +
            "where l.id=:id" )
    Set<ModuleEntity> findAllByUserId(Long id);
}
