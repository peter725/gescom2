package es.dgc.gesco.model.modules.email.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;

import es.dgc.gesco.model.modules.user.db.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import static es.dgc.gesco.model.util.ConstanteBD.*;


/**
 * @author serikat
 */
@Entity
@Table(name = TABLE_EMAIL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Email extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_EMAIL)
    @SequenceGenerator(
            name = SEQ_EMAIL,
            sequenceName = SEQ_EMAIL,
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "email" )
    @NotNull(message = "Debes especificar el email")
    @Size(min = 1, max = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

}
