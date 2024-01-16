package es.consumo.gescom.modules.arbitration.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "arbitration")
public class ArbitrationEntity extends SimpleEntity {
    @CreatedDate
    @Basic
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Basic
    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;
    @Basic
    @Column(name = "reason", nullable = true, length = 500)
    private String reason;
    @Basic
    @Column(name = "aspiration", nullable = true, length = 500)
    private String aspiration;
    @Basic
    @Column(name = "observation", nullable = true, length = 500)
    private String observation;
    @Basic
    @Column(name = "causes", nullable = true, length = 500)
    private String causes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arbitration_board_id", nullable = true)
    private ArbitrationBoardEntity arbitrationBoard;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claimant_id", nullable = false)
    private ClaimantEntity claimant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_type_id", nullable = true)
    private NotificationTypeEntity notificationType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claimed_id", nullable = true)
    private ClaimedEntity claimed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arbitration_status_id", nullable = true)
    private ArbitrationStatusEntity status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArbitrationEntity that = (ArbitrationEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(createAt, that.createAt)
                && Objects.equals(updateAt, that.updateAt) && Objects.equals(getClaimant(), that.getClaimant())
                && Objects.equals(getArbitrationBoard(), that.getArbitrationBoard()) && Objects.equals(getNotificationType(), that.getNotificationType())
                && Objects.equals(reason, that.reason) && Objects.equals(aspiration, that.aspiration) && Objects.equals(causes, that.causes)
                && Objects.equals(observation, that.observation) && Objects.equals(claimed, that.getClaimed())
                && Objects.equals(status, that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), createAt, updateAt, getClaimant(), getArbitrationBoard(),
                getNotificationType(), reason, aspiration, observation, causes, getClaimed(), getStatus());
    }

    public interface SimpleProjection {
        Long getId();

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime getCreateAt();

        String getBoardName();

        String getClaimant();

        String getClaimantLastname();

        String getClaimantDni();

        String getNotificationType();

        String getClaimedNif();

        String getClaimedSocialReason();
        
        String getCauses();

        ArbitrationStatusEntity.SimpleProjection getStatus();
    }
}
