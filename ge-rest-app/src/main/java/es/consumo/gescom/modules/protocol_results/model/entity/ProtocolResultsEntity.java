package es.consumo.gescom.modules.protocol_results.model.entity;

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
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "protocol_results")
public class ProtocolResultsEntity extends SimpleEntity {

    @Column(name = "AUTONOMOUS_COMMUNITY_COUNTRY_CODE")
    @Size(min = 1, max = 10)
    private String autonomousCommunityCountryCode;

    @Column(name = "AUTONOMOUS_COMMUNITY_COUNTRY_ID")
    private Long autonomousCommunityCountryId;

    @Column(name = "PRODUCT_SERVICE_CODE")
    @Size(min = 1, max = 10)
    private String productServiceCode;

    @Column(name = "CAMPAIGN_ID")
    private Long campaignId;

    @Column(name = "PROTOCOL_CODE")
    @Size(min = 1, max = 10)
    private String protocolCode;

    @Column(name = "PRODUCT_SERVICE_ID")
    private Long productServiceId;

    @Column(name = "PROTOCOL_ID")
    private Long protocolId;

    @Column(name = "CODE")
    @Size(min = 1, max = 10)
    private String code;

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
    @Setter
    private Integer state = 1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProtocolResultsEntity that = (ProtocolResultsEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

    public interface SimpleProjection {

        Long getId();
        String getAutonomousCommunityCountryCode();
        Long getAutonomousCommunityCountryId();
        String getProductServiceCode();
        Long getCampaignId();
        String getProtocolCode();
        Long getProductServiceId();
        Long getProtocolId();
        String getCode();

    }
}


