package es.consumo.gescom.modules.timer.model.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import es.consumo.gescom.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "timer")
public class TimerEntity extends SimpleEntity {

    @CreatedDate
    @Basic
    @Column(name = "max_days", nullable = false)
    private Integer maxDays;
    @Basic
    @Column(name = "min_days", length = 300)
    private Integer minDays;
    @Basic
    @Column(name = "procedure_name", length = 300)
    private String procedureName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimerEntity that = (TimerEntity) o;
        return Objects.equals(maxDays, that.maxDays) && Objects.equals(minDays, that.minDays) 
        && Objects.equals(procedureName, that.procedureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxDays, minDays, procedureName);
    }

    public interface SimpleProjection {
        Long getId();

        Integer getMaxDays();

        Integer getMinDays();

        String getProcedureName();
    }
}
