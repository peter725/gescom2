package es.dgc.gesco.model.modules.profile.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import static es.dgc.gesco.model.util.ConstanteBD.*;

@Entity
@Table(name = TABLE_PROFILE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Profile extends AuditedBaseEntity {

    //crea el campo id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_PROFILE)
    @SequenceGenerator(
            name = SEQ_PROFILE,
            sequenceName = SEQ_PROFILE,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    //crea el campo name de tipo string notnull maximo 20
    @Column(name = "NAME" )
    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 20)
    private String name;


}
