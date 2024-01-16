package es.consumo.gescom.modules.notification.model.criteria;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.modules.arbitration.model.entity.NotificationTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationCriteria extends FilterCriteria {

    private Boolean status;
    private String process;
    private String message;
    private String recipient;
    private Integer priority;
    private String origin;
    private LocalDateTime lastSeen;
    private NotificationTypeEntity notificationTypeEntity;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAtLTE;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAtGTE;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAtLTE;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAtGTE;

    
}
