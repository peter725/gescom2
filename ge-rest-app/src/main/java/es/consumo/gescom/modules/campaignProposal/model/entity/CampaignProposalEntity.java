package es.consumo.gescom.modules.campaignProposal.model.entity;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "campaign_proposal")
@Getter
@Setter
public class CampaignProposalEntity extends SimpleEntity {

    @Column(name = "SENT")
    private Boolean sent;

    @Column(name = "AUTONOMOUS_COMMUNITY_ID")
    private Long autonomousCommunityId;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CAMPAIGN_TYPE_ID")
    private Long campaignTypeId;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "APPROACH")
    private String approach;

    @Column(name = "JUSTIFICATION")
    private String justification;

    @Column(name = "OBJECTIVE")
    private String objective;

    @Column(name = "VIABILITY")
    private String viability;

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
            CampaignProposalEntity that = (CampaignProposalEntity) o;
            return Objects.equals(getId(), that.getId()) && Objects.equals(approach, that.approach);
        }

    @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), getId());
        }

        public interface SimpleProjection {

            Long getId();
            Boolean getSent();
            Long getAutonomousCommunityId();
            String getAutonomousCommunityName();
            Long getUserId();
            Long getCampaignTypeId();
            String getCampaignTypeName();
            LocalDate getDate();
            String getApproach();
            String getJustification();
            String getObjective();
            String getViability();

        }
}
