package es.consumo.gescom.modules.phase.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;

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
@Table(name = "phase_campaign")
@Getter
@Setter
public class PhaseEntity extends SimpleEntity {

    public static final String SEQ_PHASE_CAMPAIGN ="seq_phase_campaign";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_PHASE_CAMPAIGN)
    @SequenceGenerator(
            name = SEQ_PHASE_CAMPAIGN,
            sequenceName = SEQ_PHASE_CAMPAIGN,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHASE")
    private String phase;

    @Column(name = "DESCRIPTION")
    private String description;

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


    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            PhaseEntity that = (PhaseEntity) o;
            return Objects.equals(getId(), that.getId()) && Objects.equals(id, that.id);
        }

    @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), getId());
        }

        public interface SimpleProjection {

            Long getId();
           String getPhase();
           String getDescription();
        }
}
