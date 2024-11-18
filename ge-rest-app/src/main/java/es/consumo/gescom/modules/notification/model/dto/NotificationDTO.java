package es.consumo.gescom.modules.notification.model.dto;

import java.time.LocalDateTime;

import es.consumo.gescom.modules.arbitration.model.entity.NotificationTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Boolean status;
    private String process;
    private String message;
    private String recipient;
    private Integer priority;
    private String origin;
    private LocalDateTime lastSeen;
    private NotificationTypeEntity notificationTypeEntity;
}
