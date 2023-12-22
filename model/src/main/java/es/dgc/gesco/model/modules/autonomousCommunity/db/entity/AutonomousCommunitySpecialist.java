package es.dgc.gesco.model.modules.autonomousCommunity.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.model.modules.specialist.db.entity.Specialist;
import lombok.*;

import javax.persistence.*;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_CCAA_SPECIALIST;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_CCAA_SPECIALIST;


@Entity
@Table(name = TABLE_CCAA_SPECIALIST)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AutonomousCommunitySpecialist extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_CCAA_SPECIALIST)
    @SequenceGenerator(
            name = SEQ_CCAA_SPECIALIST,
            sequenceName = SEQ_CCAA_SPECIALIST,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_AUTONOMOUS_COMMUNITY")
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "ID_CAMPAIGN")
    private Campaign campaign;

}

