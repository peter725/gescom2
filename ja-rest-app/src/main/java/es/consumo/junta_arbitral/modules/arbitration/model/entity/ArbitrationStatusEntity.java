package es.consumo.junta_arbitral.modules.arbitration.model.entity;

import es.consumo.junta_arbitral.commons.db.entity.SimpleEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.constants.RequestStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "arbitration_status")
public class ArbitrationStatusEntity extends SimpleEntity {

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = true)
//    @Type(type = PostgreSQLEnumType.class)
    @ColumnTransformer(write = "?::code")
    private RequestStatus code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArbitrationStatusEntity that = (ArbitrationStatusEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    public interface SimpleProjection {
        String getId();

        String getName();

    }
}
