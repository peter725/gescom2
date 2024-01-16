package es.consumo.gescom.jwt.rest.user.db.entity;

import es.consumo.gescom.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role_has_module")
public class RoleHasModuleEntity extends SimpleEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private PermissionEntity permission;
    @Basic
    @Column(name = "role_id")
    private Long roleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private ModuleEntity module;
    @Column(name = "scope", nullable = false)
    private String scope;

    public interface FullAuthoritiesProjection {
        String getPermissionCode();

        String getModuleCode();

        String getScope();
    }
}
