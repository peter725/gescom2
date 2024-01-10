package es.consumo.junta_arbitral.modules.notification.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import es.consumo.junta_arbitral.commons.db.entity.SimpleEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.NotificationTypeEntity;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "notification")
public class NotificationEntity extends SimpleEntity {

    @CreatedDate
    @Basic
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Basic
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @Basic
    @Column(name = "status", nullable = false)
    private Boolean status;
    @Basic
    @Column(name = "process_execution", length = 300)
    private String process;
    @Basic
    @Column(name = "message", length = 300)
    private String message;
    @Basic
    @Column(name = "recipient_user", length = 300)
    private String recipient;
    @Basic
    @Column(name = "priority", length = 300)
    private Integer priority;
    @Basic
    @Column(name = "origin", length = 300)
    private String origin;
    @Basic
    @Column(name = "last_seen", length = 300)
    private LocalDateTime lastSeen;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_type_id ", nullable = false)
    private NotificationTypeEntity notificationTypeEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arbitration_id ", nullable = false)
    private ArbitrationEntity arbitrationEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NotificationEntity that = (NotificationEntity) o;
        return Objects.equals(createAt, that.createAt) && Objects.equals(updateAt, that.updateAt) 
        && Objects.equals(status, that.status) && Objects.equals(process, that.process)
        && Objects.equals(message, that.message)&& Objects.equals(recipient, that.recipient)
        && Objects.equals(priority, that.priority) && Objects.equals(origin, that.origin)
        && Objects.equals(getNotificationTypeEntity(), that.getNotificationTypeEntity())
        && Objects.equals(getArbitrationEntity(), that.getArbitrationEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createAt, updateAt, status, process, message, recipient, priority, origin, notificationTypeEntity, arbitrationEntity);
    }

    public interface SimpleProjection {
        Long getId();

        Boolean getStatus();

        String getProcess();

        String getMessage();

        String getRecipient();

        Integer getPriority();
        
        String getOrigin();

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime getCreateAt();

        NotificationTypeEntity.SimpleProjection getNotificationTypeEntity();

        ArbitrationEntity.SimpleProjection getArbitrationEntity();
    }
}

