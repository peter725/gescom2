package es.dgc.gesco.model.modules.field.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_FIELD;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_FIELD;

/**
 * @author serikat
 */
@Entity
@Table(name = TABLE_FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Field extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_FIELD)
    @SequenceGenerator(
            name = SEQ_FIELD,
            sequenceName = SEQ_FIELD,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIELD")
    @Size(min = 1, max = 10)
    private String field;

}