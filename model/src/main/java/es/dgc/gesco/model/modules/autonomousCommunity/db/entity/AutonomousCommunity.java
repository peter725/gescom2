package es.dgc.gesco.model.modules.autonomousCommunity.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import static es.dgc.gesco.model.util.ConstanteBD.*;

@Entity
@Table(name = TABLE_CCAA)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AutonomousCommunity extends AuditedBaseEntity {

    //genera el campo id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_CCAA)
    @SequenceGenerator(
            name = SEQ_CCAA,
            sequenceName = SEQ_CCAA,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    //genera el campo name
    @Column(name = "NAME" )
    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 50)
    private String name;

}
