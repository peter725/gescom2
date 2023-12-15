package es.dgc.gesco.model.modules.campaign.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import es.dgc.gesco.model.modules.CampaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.field.db.entity.Field;
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

    @Column(name = "YEAR" )
    @NotNull(message = "Debes especificar el año")
    @Size(min = 1, max = 100)
    private Long year;

    @Column(name = "CPA")
    @Size(min = 1, max = 10)
    private String cpa;

    @Column(name = "NAME_CAMPAIGN")
    @NotNull(message = "Debes especificar el nombre de la campaña")
    @Size(min = 1, max = 10)
    private String name_campaign;

    @ManyToOne
    @JoinColumn(name = "TYPE_CAMPAIGN")
    @NotNull(message = "Debes seleccionar el tipo de campaña")
    private CampaignType id_type_campaign;

    @ManyToOne
    @JoinColumn(name = "ID_FIELD")
    @NotNull(message = "Debes seleccionar el ámbito")
    private Field id_field;

    @ManyToOne
    @JoinColumn(name = "ID_AUTONOMOUS_COMMUNITY_RESPONSIBLE")
    @NotNull(message = "Debes seleccionar la CCAA responsable")
    private AutonomousCommunity id_autonomous_community_responsible;

    @ManyToOne
    @JoinColumn(name = "ID_PHASE")
    private PhaseCampaign id_phase;

}