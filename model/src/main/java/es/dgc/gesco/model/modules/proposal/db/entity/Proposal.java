package es.dgc.gesco.model.modules.proposal.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import es.dgc.gesco.model.modules.user.db.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static es.dgc.gesco.model.util.ConstanteBD.SEQ_ROLE;
import static es.dgc.gesco.model.util.ConstanteBD.TABLE_ROLE;

@Entity
@Table(name = TABLE_ROLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Proposal extends AuditedBaseEntity {

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

    @Column(name = "ID_CCAA")
    private Long id_ccaa;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @Column(name = "ID_CAMPAIGNTYPE")
    private Long idCampaignType;

    @Column(name = "DATE", nullable = false)
    private LocalDateTime date;

    @Column(name = "PROPOSAL")
    private String proposal;

    @Column(name = "JUSTIFICATION")
    private String justification;

    @Column(name = "OBJECTIVE")
    private String objective;

    @Column(name = "VIABILITY")
    private String viability;

}