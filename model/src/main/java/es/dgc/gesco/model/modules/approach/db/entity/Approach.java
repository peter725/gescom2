package es.dgc.gesco.model.modules.approach.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import javax.persistence.*;

import es.dgc.gesco.model.modules.CampaignType.db.entity.CampaingnType;
import lombok.*;

import java.time.LocalDate;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_APPROACH)
    @SequenceGenerator(
            name = SEQ_APPROACH,
            sequenceName = SEQ_APPROACH,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "SENT")
    private Boolean sent;

    @Column(name = "AUTONOMOUS_COMMUNITY_ID")
    private Long autonomous_community_id;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
    @Column(name = "USER_ID")
    private Long userId;


    @Column(name = "CAMPAIGN_TYPE_ID")
    private Long campaignTypeId;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "APPROACH")
    private String approach;

    @Column(name = "JUSTIFICATION")
    private String justification;

    @Column(name = "OBJECTIVE")
    private String objective;

    @Column(name = "VIABILITY")
    private String viability;

}