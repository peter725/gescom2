package es.dgc.gesco.model.modules.campaign.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import es.dgc.gesco.model.modules.campaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.ambit.db.entity.Ambit;
import es.dgc.gesco.model.modules.phase.db.entity.PhaseCampaign;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static es.dgc.gesco.model.util.ConstanteBD.*;


/**
 * @author serikat
 */
@Entity
@Table(name = TABLE_CAMPAIGN)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Campaign extends AuditedBaseEntity {

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
    @Size(min = 1, max = 100)
    private Long year;

    @Column(name = "CPA")
    @Size(min = 1, max = 10)
    private String cpa;

    @Column(name = "NAME_CAMPAIGN")
    @NotNull(message = "Debes especificar el nombre de la campaña")
    @Size(min = 1, max = 10)
    private String nameCampaign;

    @ManyToOne
    @JoinColumn(name = "TYPE_CAMPAIGN_ID")
    @NotNull(message = "Debes seleccionar el tipo de campaña")
    private CampaignType typeCampaign;

    @ManyToOne
    @JoinColumn(name = "AMBIT_ID")
    @NotNull(message = "Debes seleccionar el ámbito")
    private Ambit ambit;

    @ManyToOne
    @JoinColumn(name = "AUTONOMOUS_COMMUNITY_RESPONSIBLE_ID")
    @NotNull(message = "Debes seleccionar la CCAA responsable")
    private AutonomousCommunity autonomousCommunityResponsible;

    @ManyToOne
    @JoinColumn(name = "PHASE_ID")
    private PhaseCampaign phase;

}