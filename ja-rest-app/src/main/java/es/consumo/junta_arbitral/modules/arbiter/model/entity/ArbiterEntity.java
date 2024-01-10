package es.consumo.junta_arbitral.modules.arbiter.model.entity;

import es.consumo.junta_arbitral.commons.db.entity.SimpleEntity;
import es.consumo.junta_arbitral.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "arbiter")
@SQLDelete(sql = "UPDATE arbiter SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
public class ArbiterEntity extends SimpleEntity {
    @CreatedDate
    @Basic
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Basic
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @Basic
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Basic
    @Column(name = "surnames", length = 100)
    private String surnames;
    @Basic
    @Column(name = "nif", length = 20)
    private String nif;
    @Basic
    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arbitration_board_id", nullable = true)
    private ArbitrationBoardEntity arbitrationBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arbiter_type_id", nullable = true)
    private ArbiterTypeEntity arbiterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collegiate_type_id")
    private CollegiateTypeEntity collegiateType;

    @Basic
    @Column(name = "deleted")
    private Boolean deleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArbiterEntity that = (ArbiterEntity) o;
        return Objects.equals(createAt, that.createAt) && Objects.equals(updateAt, that.updateAt) 
        && Objects.equals(name, that.name) && Objects.equals(surnames, that.surnames) 
        && Objects.equals(nif, that.nif) && Objects.equals(getArbiterType(), that.getArbiterType()) 
        && Objects.equals(getArbitrationBoard(), that.getArbitrationBoard())
        && Objects.equals(getCollegiateType(), that.getCollegiateType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createAt, updateAt, name, surnames, nif);
    }

    public interface SimpleProjection {
        Long getId();

        String getName();

        String getSurnames();

        String getNif();

        String getEmail();

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime getCreateAt();

        ArbiterTypeEntity.SimpleProjection getArbiterType();

        ArbitrationBoardEntity.SimpleProjection getArbitrationBoard();

        CollegiateTypeEntity.SimpleProjection getCollegiateType();
    }


}


