package es.dgc.gesco.model.modules.user.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;

import es.dgc.gesco.model.commom.validation.constraints.NIF;
import es.dgc.gesco.model.modules.email.db.entity.Email;
import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import es.dgc.gesco.model.modules.phone.db.entity.Phone;
import es.dgc.gesco.model.modules.role.db.entity.Role;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.List;

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

    @Column(name = "FIRST_SURNAME")
    @NotNull(message = "Debes especificar el primer apellido")
    @Size(min = 1, max = 100, message = "El primer apellido debe medir entre 1 y 100")
    private String firstSurname;

    @Column(name = "SECOND_SURNAME")
    @Size(min = 1, max = 100, message = "El segundo apellido debe medir entre 1 y 100")
    private String secondSurname;

    @Column(name = "NIF", unique = true, length = 10)
    @NIF
    private String nif;

    @Column(name = "POSITION", length = 100)
    private String position;

    @ManyToOne
    @JoinColumn(name = "AUTHORITY_ID")
    private AuthorityOEU authorityOEU;

    //declara campo area_responsability de tipo string notnull maximo 100
    @Column(name = "AREA_RESPONSABILITY" )
    @NotNull(message = "Debes especificar el area de responsabilidad")
    @Size(min = 1, max = 100)
    private String areaResponsability;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @Transient
    @ToString.Exclude
    private List<Email> emails;

    @Transient
    @ToString.Exclude
    private List<Phone> phones;



}

