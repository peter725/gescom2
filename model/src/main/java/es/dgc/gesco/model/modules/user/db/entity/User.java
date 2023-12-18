package es.dgc.gesco.model.modules.user.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.profile.db.entity.Profile;
import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.dgc.gesco.model.modules.userType.db.entity.UserType;
import lombok.*;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_USER;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_USER;

/**
 * @author serikat
 */
@Entity
@Table(name = TABLE_USER)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class User extends AuditedBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_USER)
    @SequenceGenerator(
            name = SEQ_USER,
            sequenceName = SEQ_USER,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME" )
    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 100)
    private String name;

    @Column(name = "PASSWORD")
    @NotNull(message = "Debes especificar la contraseña")
    @Size(min = 1, max = 10)
    private String password;

    @Column(name = "EMAIL", unique = true)
    @NotNull(message = "Debes especificar el email")
    @Size(min = 1, max = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "CCAA_ID")
    private AutonomousCommunity autonomousCommunity;

    @ManyToOne
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "USER_TYPE_ID")
    private UserType userType;

    @Column(name = "PHONE", length = 15)
    @Size(min = 1, max = 15, message = "El teléfono debe medir 10 caracteres")
    private String phone;

    @Column(name = "FIRST_SURNAME")
    @NotNull(message = "Debes especificar el primer apellido")
    @Size(min = 1, max = 100, message = "El primer apellido debe medir entre 1 y 100")
    private String firstSurname;

    @Column(name = "SECOND_SURNAME")
    @Size(min = 1, max = 100, message = "El segundo apellido debe medir entre 1 y 100")
    private String secondSurname;

    @Column(name = "NIF", unique = true)
    @NotNull(message = "Debes especificar el NIF")
    @Size(min = 1, max = 9, message = "El NIF debe medir 9 caracteres")
    private String nif;



}

