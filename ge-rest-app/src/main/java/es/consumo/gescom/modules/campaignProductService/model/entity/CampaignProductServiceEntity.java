package es.consumo.gescom.modules.campaignProductService.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "campaign_product_service")
public class CampaignProductServiceEntity extends SimpleEntity {

    @EqualsAndHashCode.Include
    @Column(name = "CAMPAIGN_ID")
    private Long campaignId;

    @EqualsAndHashCode.Include
    @Column(name = "CODE_PRODUCT_SERVICE")
    private String codeProductService;

    @Column(name = "BK_CPNOPS")
    private String bkCpnops;

    @Column(name = "CODE")
    private String code;

    @Column(name = "PRODUCT_SERVICE_ID")
    private Long productServiceId;

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
    private Integer state = 1;


    public interface SimpleProjection {

        Long getId();
        String getCode();

    }
}


