package es.dgc.gesco.model.commom.db.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Basic entity structure with a predefined ID using IDENTITY generator strategy and provides a state field.
 */
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class SimpleStatefulEntity extends SimpleEntity implements StatefulEntity {

    @Column(name = "ID_STATE", nullable = false)
    @Setter
    private Integer state = 1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleStatefulEntity)) return false;
        if (!super.equals(o)) return false;
        SimpleStatefulEntity that = (SimpleStatefulEntity) o;
        return Objects.equals(getState(), that.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getState());
    }
}

