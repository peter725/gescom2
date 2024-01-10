package es.consumo.junta_arbitral.commons.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Base de entidad con los campos de auditoría implementados.
 * <p>
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/index.html#auditing
 * https://stackoverflow.com/questions/29472931/how-does-createdby-work-in-spring-data-jpa
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/index.html#auditing.auditor-aware
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@EqualsAndHashCode(callSuper = false)
public abstract class AuditedBaseEntity extends StandardEntity {

    @JsonIgnore
    @CreatedDate
    @Column(name = "FALTA", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "FMODI", nullable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @CreatedBy
    @Column(name = "USUALTA", nullable = false)
    private Long createdBy;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "USUMODI", nullable = false)
    private Long updatedBy;

}
