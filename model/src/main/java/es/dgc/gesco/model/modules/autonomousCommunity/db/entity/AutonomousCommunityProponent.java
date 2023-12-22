package es.dgc.gesco.model.modules.autonomousCommunity.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;
import lombok.*;

import javax.persistence.*;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_CCAA_PROPONENT;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_CCAA_PROPONENTS;


@Entity
@Table(name = TABLE_CCAA_PROPONENTS)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AutonomousCommunityProponent extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_CCAA_PROPONENT)
    @SequenceGenerator(
            name = SEQ_CCAA_PROPONENT,
            sequenceName = SEQ_CCAA_PROPONENT,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_AUTONOMOUS_COMMUNITY")
    private Proponent proponent;

    @ManyToOne
    @JoinColumn(name = "ID_CAMPAIGN")
    private Campaign campaign;

}