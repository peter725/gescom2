package es.consumo.junta_arbitral.jwt.rest.user.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "role")
@Getter
@Setter
public class RoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @CreatedDate
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "enable", nullable = false)
    private Boolean enable;
    @Basic
    @Column(name = "visible", nullable = false)
    private Boolean visible;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<LoginEntity> logins  = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(createAt, that.createAt)) return false;
        if (!Objects.equals(updateAt, that.updateAt)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(enable, that.enable)) return false;
        return Objects.equals(visible, that.visible);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (visible != null ? visible.hashCode() : 0);
        return result;
    }
}
