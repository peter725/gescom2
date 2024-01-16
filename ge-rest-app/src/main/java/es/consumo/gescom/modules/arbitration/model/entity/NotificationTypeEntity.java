package es.consumo.gescom.modules.arbitration.model.entity;

import es.consumo.gescom.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "notification_type")
public class NotificationTypeEntity extends SimpleEntity {

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTypeEntity that = (NotificationTypeEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    public interface SimpleProjection {
        String getName();
    }
}
