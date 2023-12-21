package es.dgc.gesco.model.modules.phase.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import lombok.*;

import javax.persistence.*;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_PHASE_CAMPAIGN;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_PHASE_CAMPAIGN;

@Entity
@Table(name = TABLE_PHASE_CAMPAIGN)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PhaseCampaign extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_PHASE_CAMPAIGN)
    @SequenceGenerator(
            name = SEQ_PHASE_CAMPAIGN,
            sequenceName = SEQ_PHASE_CAMPAIGN,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHASE")
    private String phase;

    @Column(name = "DESCRIPTION")
    private String description;

}