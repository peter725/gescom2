package es.consumo.gescom.modules.infringement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "infringement")
@Getter
@Setter
public class InfringementEntity extends SimpleEntity {

    @Column(name = "CODE")
    private String code;

    @Column(name = "INFRINGEMENT")
    private String infringement;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "BK_TTITEDE")
    private String bkTtitede;

    @Column(name = "BK_TTICOSE")
    private String bkTticose;

    @Column(name = "BK_TTINOCO2")
    private String bkTtinoco2;

    @Column(name = "BK_TTIINSEL1")
    private String bkTtiinsel1;

    @Column(name = "BK_TTINOMBREE")
    private String bkTtinombree;

    @Column(name = "BK_TTINOMBREI")
    private String bkTtinombrei;

    @Column(name = "BK_TTINOMBREF")
    private String bkTtinombref;

    @Column(name = "BK_TTINOCOE")
    private String bkTtinocoe;

    @Column(name = "BK_TTINOCOI")
    private String bkTtinocoi;

    @Column(name = "BK_TTINOCOF")
    private String bkTtinocof;

    @Column(name = "BK_TTITEDEE")
    private String bkTtitedee;

    @Column(name = "BK_TTITEDEI")
    private String bkTtitedei;

    @Column(name = "BK_TTITEDEF")
    private String bkTtitedef;

    @Column(name = "BK_TTICOL4")
    private String bkTticol4;

    @Column(name = "BK_TTITENO")
    private String bkTtiteno;

    @Column(name = "BK_TTITEAR")
    private String bkTtitear;

    @Column(name = "BK_TTIINSEL2")
    private String bkTtiinsel2;

    @Column(name = "BK_TTICOICI")
    private String bkTticoici;


    @JsonIgnore
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @CreatedBy
    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "UPDATED_BY", nullable = true)
    private Long updatedBy;

    @Column(name = "ID_STATE", nullable = false)
    @Setter
    private Integer state = 1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InfringementEntity that = (InfringementEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(that.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

    public interface SimpleProjection {

        Long getId();
        String getName();

    }
}