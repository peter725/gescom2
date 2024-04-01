package es.consumo.gescom.modules.totalProtocolResults.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "total_protocol_results")
public class TotalProtocolResultsEntity extends SimpleEntity {

    @Column(name = "ccaa_ren")
    private Long ccaaRen;

    @Column(name = "ccaa_rep")
    private Long ccaaRep;

    @Column(name = "ccaa_res")
    private Long ccaaRes;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "protocol_results_code", length = 50)
    private String protocolResultsCode;

    @Column(name = "code_question")
    private String codeQuestion;

    @Column(name = "protocol_results_id")
    private Long protocolResultsId;

    @JsonIgnore
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @CreatedBy
    @Column(name = "created_by")
    private Long createdBy;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "id_state")
    @Setter
    private Integer idState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TotalProtocolResultsEntity that = (TotalProtocolResultsEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

    public interface SimpleProjection {
        Long getId();

    }
}
