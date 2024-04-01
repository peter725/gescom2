package es.consumo.gescom.modules.general.model.entity;

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
@Table(name = "road_type")
public class RoadTypeEntity extends SimpleEntity {

    @Basic
    @Column(name = "catastro", nullable = true, length = 40)
    private String catastro;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoadTypeEntity that = (RoadTypeEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(catastro, that.catastro) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), catastro, name);
    }
}
