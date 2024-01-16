package es.consumo.gescom.modules.history.model.entity;

import es.consumo.gescom.commons.db.entity.SimpleEntity;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import lombok.Getter;
import lombok.Setter;

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
@Entity
@Table(name = "history")
@Getter
@Setter
public class HistoryEntity extends SimpleEntity {

    @CreatedDate
    @Basic
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Basic
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Basic
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arbitration_board_id", insertable = true, updatable = true)
    private ArbitrationBoardEntity arbitrationBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arbitration_id", insertable = true, updatable = true)
    private ArbitrationEntity arbitration;

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            HistoryEntity that = (HistoryEntity) o;
            return Objects.equals(getCreateAt(), that.getCreateAt()) && Objects.equals(updateAt, that.updateAt) && 
            Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getArbitrationBoard(), that.getArbitrationBoard())
            && Objects.equals(getArbitration(), that.getArbitration());
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), getCreateAt(), updateAt, getDescription(), getArbitrationBoard(), getArbitration());
        }

        public interface SimpleProjection {
            Long getId();

            String getDescription();

            ArbitrationBoardEntity getArbitrationBoard();

            ArbitrationEntity getArbitration();

            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
            LocalDateTime getCreateAt();
        }
    }
