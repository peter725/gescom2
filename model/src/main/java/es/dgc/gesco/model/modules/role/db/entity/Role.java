package es.dgc.gesco.model.modules.role.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_ROLE;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_ROLE;

/**
 * @author serikat
 */
@Entity
@Table(name = TABLE_ROLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Role extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ROLE)
    @SequenceGenerator(
            name = SEQ_ROLE,
            sequenceName = SEQ_ROLE,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROLE" )
    @NotNull(message = "Debes especificar el rol")
    @Size(min = 1, max = 100)
    private String role;


}
