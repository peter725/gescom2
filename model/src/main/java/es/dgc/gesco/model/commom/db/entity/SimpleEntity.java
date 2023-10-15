package es.dgc.gesco.model.commom.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Basic entity structure with a predefined Long ID using IDENTITY generation strategy
 */
@MappedSuperclass
@Getter
@Setter
public abstract class SimpleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Override
    public String toString() {
        return "id=" + getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleEntity)) return false;
        SimpleEntity that = (SimpleEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
