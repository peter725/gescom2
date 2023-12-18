package es.dgc.gesco.model.modules.CampaignType.db.entity;

import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import lombok.*;

import javax.persistence.*;

import static es.dgc.gesco.model.util.ConstanteBD.*;

@Entity
@Table(name = TABLE_CAMPAIGN_TYPE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CampaignType extends AuditedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_CAMPAIGN_TYPE)
    @SequenceGenerator(
            name = SEQ_CAMPAIGN_TYPE,
            sequenceName = SEQ_CAMPAIGN_TYPE,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE")
    private String type;

}