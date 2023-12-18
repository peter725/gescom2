package es.dgc.gesco.model.modules.userType.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.profile.db.entity.Profile;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static es.dgc.gesco.model.util.ConstanteBD.*;
import static es.dgc.gesco.model.util.ConstanteBD.SEQ_USER;

/**
 * @author serikat
 */
@Entity
@Table(name = TABLE_USER_TYPE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserType extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_USER_TYPE)
    @SequenceGenerator(
            name = SEQ_USER_TYPE,
            sequenceName = SEQ_USER_TYPE,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME" )
    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

}
