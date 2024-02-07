package es.consumo.gescom.modules.questions.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "questions")
public class QuestionsEntity extends SimpleEntity {

    @Column(name = "CODE")
    @Size(min = 1, max = 10)
    private String code;

    @ManyToOne
    @JoinColumn(name = "PROTOCOL_CAMPAIGN_ID")
    private ProtocolEntity protocolCampaingId;

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

    @Column(name = "PROTOCOL_CAMPAIGN_CODE")
    private String protocolCampaignCode;

    @JsonIgnore
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @CreatedBy
    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "UPDATED_BY", nullable = true)
    private Long updatedBy;

    @Column(name = "ID_STATE", nullable = false)
    @Setter
    private Integer state = 1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QuestionsEntity that = (QuestionsEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

    public interface SimpleProjection {

        Long getId();
        String getCode();

    }
}


