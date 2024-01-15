package es.consumo.gescom.modules.role.model.entity;

import es.consumo.gescom.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleEntity extends SimpleEntity {
    
    @CreatedDate
    @Basic
    @Column(name = "create_at", nullable = false)
    private Timestamp createAt;
    @LastModifiedDate
    @Basic
    @Column(name = "update_at")
    private Timestamp updateAt;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "visible")
    private Boolean visible;
    @Basic
    @Column(name = "enable")
    private Boolean enable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(createAt, that.createAt) && Objects.equals(updateAt, that.updateAt)
        && Objects.equals(name, that.name)
        && Objects.equals(visible, that.visible) && Objects.equals(enable, that.enable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createAt, updateAt, name, visible, enable);
    }

    public interface SimpleProjection {
        Long getId();

        String getName();

//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
Timestamp getCreateAt();
    }
}
