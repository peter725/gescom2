package es.dgc.gesco.model.modules.autonomousCommunity.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import lombok.*;

import javax.persistence.*;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_CCAA_PARTTICIPANTS;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_CCAA_PARTTICIPANTS;

@Entity
@Table(name = TABLE_CCAA_PARTTICIPANTS)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AutonomousCommunityParticipants extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_CCAA_PARTTICIPANTS)
    @SequenceGenerator(
            name = SEQ_CCAA_PARTTICIPANTS,
            sequenceName = SEQ_CCAA_PARTTICIPANTS,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_AUTONOMOUS_COMMUNITY")
    private AutonomousCommunity id_autonomous_community;

    @ManyToOne
    @JoinColumn(name = "ID_CAMPAIGN")
    private Campaign id_campaign;
}