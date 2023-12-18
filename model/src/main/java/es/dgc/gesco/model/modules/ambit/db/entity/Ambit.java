package es.dgc.gesco.model.modules.ambit.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static es.dgc.gesco.model.util.ConstanteBD.*;

/**
 * @author serikat
 */
@Entity
@Table(name = TABLE_AMBIT)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Ambit extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_AMBIT)
    @SequenceGenerator(
            name = SEQ_AMBIT,
            sequenceName = SEQ_AMBIT,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMBIT")
    @Size(min = 1, max = 10)
    private String ambit;

}