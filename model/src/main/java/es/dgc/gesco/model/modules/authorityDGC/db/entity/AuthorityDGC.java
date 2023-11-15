package es.dgc.gesco.model.modules.authorityDGC.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.*;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_AUTHORITY_DGC;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_AUTHORITY_DGC;

@Entity
@Table(name = TABLE_AUTHORITY_DGC)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AuthorityDGC  extends AuditedBaseEntity {

    //genera el campo id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_AUTHORITY_DGC)
    @SequenceGenerator(
            name = SEQ_AUTHORITY_DGC,
            sequenceName = SEQ_AUTHORITY_DGC,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    //genera el campo name string de 50 caracteres
    @Column(name = "NAME" )
    @Size(min = 1, max = 50)
    private String name;

    //genear el campo directionName string de 50 caracteres
    @Column(name = "DIRECTION_NAME" )
    @Size(min = 1, max = 50)
    private String directionName;

    //genera el campo comunidadAutonoma string de 50 caracteres
    @Column(name = "AUTONOMOUS_COMMUNITY" )
    @Size(min = 1, max = 50)
    private String comunidadAutonoma;

    //genera el campo concierge string de 50 caracteres
    @Column(name = "CONCIERGE" )
    @Size(min = 1, max = 50)
    private String concierge;

    //genera el campo addres string de 50 caracteres
    @Column(name = "ADDRESS" )
    @Size(min = 1, max = 50)
    private String address;


}
