package es.dgc.gesco.model.modules.user.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;

import es.dgc.gesco.model.commom.validation.constraints.NIF;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import static es.dgc.gesco.model.util.ConstanteBD.SEQ_USERS;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_USERS;

/**
 * @author serikat
 */
@Entity
@Table(name = TABLE_USERS)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class User extends AuditedBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_USERS)
    @SequenceGenerator(
            name = SEQ_USERS,
            sequenceName = SEQ_USERS,
            allocationSize = 1
    )
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NIF", nullable = false, unique = true, length = 9)
    @NIF
    private String nif;

    @Column(name = "NAME", nullable = false)
    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 100)
    private String name;

    @Column(name = "FIRST_SURNAME", nullable = false)
    @NotNull(message = "Debes especificar el primer apellido")
    @Size(min = 1, max = 100, message = "El primer apellido debe medir entre 1 y 100")
    private String firstSurname;

    @Column(name = "SECOND_SURNAME")
    // @Size(min = 1, max = 100, message = "El segundo apellido debe medir entre 1 y 100")
    private String secondSurname;

    @Column(name = "EMAIL")
    @Email
    private String email;

    @Column(name = "PASSWORD")
    private String password;

}

