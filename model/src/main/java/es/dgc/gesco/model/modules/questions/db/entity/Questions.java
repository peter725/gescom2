package es.dgc.gesco.model.modules.questions.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import lombok.*;

import javax.persistence.*;

import static es.dgc.gesco.model.util.ConstanteBD.*;

@Entity
@Table(name = TABLE_QUESTIONS)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Questions extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_QUESTIONS)
    @SequenceGenerator(
            name = SEQ_QUESTIONS,
            sequenceName = SEQ_QUESTIONS,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "PROTOCOL")
    private String protocol;

    @Column(name = "BK_TRNRTIPP")
    private String bkTrnrtipp;

    @Column(name = "BK_TRCOTIPP")
    private String bkTrcotipp;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "INFRINGEMENT")
    private String infringement;

    @Column(name = "BK_TRINTI")
    private String bkTrinti;

    @Column(name = "BK_TRINRE")
    private String bkTrinre;

    @Column(name = "BK_TRREES")
    private String bkTrrees;

}
