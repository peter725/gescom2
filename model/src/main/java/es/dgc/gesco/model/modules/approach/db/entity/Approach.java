package es.dgc.gesco.model.modules.approach.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import es.dgc.gesco.model.modules.user.db.entity.User;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static es.dgc.gesco.model.util.ConstanteBD.*;

@Entity
@Table(name = TABLE_APPROACH)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Approach extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ROLE)
    @SequenceGenerator(
            name = SEQ_ROLE,
            sequenceName = SEQ_ROLE,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "SENT")
    private Boolean sent;

    @Column(name = "ID_AUTONOMOUS_COMMUNITY")
    private Long id_autonomous_community;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @Column(name = "ID_CAMPAIGN_TYPE")
    private Long idCampaignType;

    @Column(name = "DATE", nullable = false)
    private LocalDateTime date;

    @Column(name = "APPROACH")
    private String approach;

    @Column(name = "JUSTIFICATION")
    private String justification;

    @Column(name = "OBJECTIVE")
    private String objective;

    @Column(name = "VIABILITY")
    private String viability;

}