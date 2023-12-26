package es.dgc.gesco.model.modules.proponent.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import lombok.*;

import javax.persistence.*;

import static es.dgc.gesco.model.util.ConstanteBD.*;

@Entity
@Table(name = TABLE_PROPONENT)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Proponent extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_PROPONENT)
    @SequenceGenerator(
            name = SEQ_PROPONENT,
            sequenceName = SEQ_PROPONENT,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROPONENT")
    private String name;
}