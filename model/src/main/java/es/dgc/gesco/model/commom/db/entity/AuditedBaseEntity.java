package es.dgc.gesco.model.commom.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


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
@Setter
@EqualsAndHashCode(callSuper = false)
public abstract class AuditedBaseEntity extends StandardEntity {

    @JsonIgnore
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "UPDATE_AT", nullable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @CreatedBy
    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "UPDATE_BY", nullable = true)
    private Long updatedBy;

}
