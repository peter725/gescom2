package es.consumo.gescom.modules.campaign.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.campaignType.model.entity.CampaignTypeEntity;
import es.consumo.gescom.modules.phase.model.entity.PhaseEntity;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "campaign")
@Getter
@Setter
public class CampaignEntity extends SimpleEntity {

    public static final String SEQ_CAMPAIGN="seq_campaign";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_CAMPAIGN)
    @SequenceGenerator(
            name = SEQ_CAMPAIGN,
            sequenceName = SEQ_CAMPAIGN,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "YEAR_CAMPAIGN" )
    @NotNull(message = "Debes especificar el año")
    private Long year;

    @Column(name = "CPA")
    @Size(min = 1, max = 30)
    private String codeCpa;

    @Column(name = "NAME_CAMPAIGN")
    @NotNull(message = "Debes especificar el nombre de la campaña")
    @Size(min = 1, max = 100)
    private String nameCampaign;

    @ManyToOne
    @JoinColumn(name = "TYPE_CAMPAIGN_ID")
    @NotNull(message = "Debes seleccionar el tipo de campaña")
    private CampaignTypeEntity campaignType;

    @ManyToOne
    @JoinColumn(name = "AMBIT_ID")
    @NotNull(message = "Debes seleccionar el ámbito")
    private AmbitEntity ambit;

    @ManyToOne
    @JoinColumn(name = "AUTONOMOUS_COMMUNITY_RESPONSIBLE_ID")
    @NotNull(message = "Debes seleccionar la CCAA responsable")
    private AutonomousCommunityEntity autonomousCommunityResponsible;

    @ManyToOne
    @JoinColumn(name = "PHASE_ID")
    private PhaseEntity phaseCampaign;

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


    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            CampaignEntity that = (CampaignEntity) o;
            return Objects.equals(getNameCampaign(), that.getNameCampaign()) && Objects.equals(nameCampaign, that.nameCampaign);
        }

    @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), getNameCampaign());
        }

        public interface SimpleProjection {

            Long getId();
            String getName();

        }
}
