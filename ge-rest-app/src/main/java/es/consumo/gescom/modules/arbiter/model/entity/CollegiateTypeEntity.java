package es.consumo.gescom.modules.arbiter.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.consumo.gescom.commons.db.entity.SimpleEntity;

@Getter
@Setter
@Entity
@Table(name = "collegiate_type")
public class CollegiateTypeEntity extends SimpleEntity {

    @Basic
    @Column(name = "create_at", nullable = false)
    private Timestamp createAt;
    @Basic
    @Column(name = "update_at")
    private Timestamp updateAt;
    @Basic
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arbiter_type_id")
    private ArbiterTypeEntity arbiterTypeEntity;

    public interface SimpleProjection {
        Long getId();
        String getName();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CollegiateTypeEntity that = (CollegiateTypeEntity) o;
        return Objects.equals(createAt, that.createAt) && Objects.equals(updateAt, that.updateAt) && Objects.equals(name, that.name) && Objects.equals(arbiterTypeEntity, that.arbiterTypeEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createAt, updateAt, name, arbiterTypeEntity);
    }
}