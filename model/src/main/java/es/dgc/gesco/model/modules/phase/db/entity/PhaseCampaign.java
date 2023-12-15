package es.dgc.gesco.model.modules.phase.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static es.dgc.gesco.model.util.ConstanteBD.SEC_PHASE_CAMPAIGN;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEC_PHASE_CAMPAIGN)
    @SequenceGenerator(
            name = SEC_PHASE_CAMPAIGN,
            sequenceName = SEC_PHASE_CAMPAIGN,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHASE")
    private String phase;

    @Column(name = "DESCRIPTION")
    private String description;

}