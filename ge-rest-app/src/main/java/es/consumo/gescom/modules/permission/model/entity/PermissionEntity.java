package es.consumo.gescom.modules.permission.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "permission")
public class PermissionEntity extends SimpleEntity {
    @JsonIgnore
    @CreatedDate
    @Basic
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "visible")
    private Boolean visible;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PermissionEntity that = (PermissionEntity) o;
        return Objects.equals(createAt, that.createAt)
                && Objects.equals(name, that.name) && Objects.equals(code, that.code)
                && Objects.equals(visible, that.visible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createAt, name, code, visible);
    }

    public interface SimpleProjection {
        Long getId();

        String getName();

        String getCode();

        Boolean getVisible();

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime getCreateAt();

    }
}
