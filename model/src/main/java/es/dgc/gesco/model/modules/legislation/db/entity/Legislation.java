package es.dgc.gesco.model.modules.legislation.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import javax.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static es.dgc.gesco.model.util.ConstanteBD.*;

@Entity
@Table(name = TABLE_LEGISLATION)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Legislation extends AuditedBaseEntity {

    //genera el campo id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_LEGISLATION)
    @SequenceGenerator(
            name = SEQ_LEGISLATION,
            sequenceName = SEQ_LEGISLATION,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    //genera el campo name
    @Column(name = "NAME" )
    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 100)
    private String name;

}
