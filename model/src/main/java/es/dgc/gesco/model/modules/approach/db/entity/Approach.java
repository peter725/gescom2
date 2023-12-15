package es.dgc.gesco.model.modules.approach.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_CAMPAIGN_PROPOSAL)
    @SequenceGenerator(
            name = SEQ_CAMPAIGN_PROPOSAL,
            sequenceName = SEQ_CAMPAIGN_PROPOSAL,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "SENT")
    private Boolean sent;

    @Column(name = "AUTONOMOUS_COMMUNITY_ID")
    private Long autonomousCommunityId;


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