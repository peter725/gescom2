package es.dgc.gesco.model.modules.infringement.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import lombok.*;

import javax.persistence.*;

import static es.dgc.gesco.model.util.ConstanteBD.*;

@Entity
@Table(name = TABLE_INFRINGEMENT)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Infringement extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_INFRINGEMENT)
    @SequenceGenerator(
            name = SEQ_INFRINGEMENT,
            sequenceName = SEQ_INFRINGEMENT,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "INFRINGEMENT")
    private String infringement;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "BK_TTITEDE")
    private String bkTtitede;

    @Column(name = "BK_TTICOSE")
    private String bkTticose;

    @Column(name = "BK_TTINOCO2")
    private String bkTtinoco2;

    @Column(name = "BK_TTIINSEL1")
    private String bkTtiinsel1;

    @Column(name = "BK_TTINOMBREE")
    private String bkTtinombree;

    @Column(name = "BK_TTINOMBREI")
    private String bkTtinombrei;

    @Column(name = "BK_TTINOMBREF")
    private String bkTtinombref;

    @Column(name = "BK_TTINOCOE")
    private String bkTtinocoe;

    @Column(name = "BK_TTINOCOI")
    private String bkTtinocoi;

    @Column(name = "BK_TTINOCOF")
    private String bkTtinocof;

    @Column(name = "BK_TTITEDEE")
    private String bkTtitedee;

    @Column(name = "BK_TTITEDEI")
    private String bkTtitedei;

    @Column(name = "BK_TTITEDEF")
    private String bkTtitedef;

    @Column(name = "BK_TTICOL4")
    private String bkTticol4;

    @Column(name = "BK_TTITENO")
    private String bkTtiteno;

    @Column(name = "BK_TTITEAR")
    private String bkTtitear;

    @Column(name = "BK_TTIINSEL2")
    private String bkTtiinsel2;

    @Column(name = "BK_TTICOICI")
    private String bkTticoici;

}