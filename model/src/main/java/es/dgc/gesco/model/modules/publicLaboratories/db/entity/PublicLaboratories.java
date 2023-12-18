package es.dgc.gesco.model.modules.publicLaboratories.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

import static es.dgc.gesco.model.util.ConstanteBD.*;

@Entity
@Table(name = TABLE_PUBLIC_LABORATORIES)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PublicLaboratories extends AuditedBaseEntity {

    //genera el campo id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_PUBLIC_LABORATORIES)
    @SequenceGenerator(
            name = SEQ_PUBLIC_LABORATORIES,
            sequenceName = SEQ_PUBLIC_LABORATORIES,
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
