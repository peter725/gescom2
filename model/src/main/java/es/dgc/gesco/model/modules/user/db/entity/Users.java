package es.dgc.gesco.model.modules.user.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class Users extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_USERS)
    @SequenceGenerator(
            name = SEQ_USERS,
            sequenceName = SEQ_USERS,
            allocationSize = 1
    )
    @Column(name = "ID", nullable = false)
    private Long id;

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

    public String getFullName() {
        String fullName = getName() + " " + getFirstSurname();
        return fullName;
    }

    @Override
    public void update(Object source) {
        Users s = (Users) source;
        setName(s.getName());
        setFirstSurname(s.getFirstSurname());
        setEmail(s.getEmail());

    }
}

