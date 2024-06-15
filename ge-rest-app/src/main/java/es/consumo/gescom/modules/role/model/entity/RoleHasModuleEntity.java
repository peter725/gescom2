package es.consumo.gescom.modules.role.model.entity;

import es.consumo.gescom.commons.db.entity.SimpleEntity;
import es.consumo.gescom.modules.module.model.entity.ModuleEntity;
import es.consumo.gescom.modules.permission.model.entity.PermissionEntity;
import es.consumo.gescom.modules.role.model.constants.PermissionScope;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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

    @Enumerated(EnumType.STRING)
    @Column(name = "scope", nullable = false)
//   @Type(type = PostgreSQLEnumType.class)
    @ColumnTransformer(write = "?::permission_scope_type")
    private PermissionScope scope;

    public interface FullAuthoritiesProjection {
        String getPermissionCode();

        String getModuleCode();

        String getScope();
    }
}
