package es.dgc.gesco.model.modules.phone.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import es.dgc.gesco.model.modules.user.db.entity.User;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_PHONE;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_PHONE;

/**
 * @author serikat
 */
@Entity
@Table(name = TABLE_PHONE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Phone extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_PHONE)
    @SequenceGenerator(
            name = SEQ_PHONE,
            sequenceName = SEQ_PHONE,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHONE" )
    @NotNull(message = "Debes especificar el Teléfono")
    @Size(min = 1, max = 100)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

}