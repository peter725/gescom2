package es.dgc.gesco.model.modules.specialist.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import lombok.*;

import javax.persistence.*;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_SPECIALIST;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_SPECIALIST;


@Entity
@Table(name = TABLE_SPECIALIST)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Specialist extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_SPECIALIST)
    @SequenceGenerator(
            name = SEQ_SPECIALIST,
            sequenceName = SEQ_SPECIALIST,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "SPECIALIST")
    private String specialist;
}