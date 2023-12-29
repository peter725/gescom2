package es.dgc.gesco.model.modules.protocol.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import lombok.*;

import javax.persistence.*;

import static es.dgc.gesco.model.util.ConstanteBD.*;

@Entity
@Table(name = TABLE_PROTOCOL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Protocol extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_PROTOCOL)
    @SequenceGenerator(
            name = SEQ_PROTOCOL,
            sequenceName = SEQ_PROTOCOL,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "PROTOCOL")
    private String protocol;

    @Column(name = "BK_TPCPTMO")
    private String bkTpcotmo;

    @Column(name = "CAMPAING_ID")
    private Long campaignId;

}