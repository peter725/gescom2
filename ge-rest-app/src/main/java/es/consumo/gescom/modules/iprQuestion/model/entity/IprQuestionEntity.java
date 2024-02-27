package es.consumo.gescom.modules.iprQuestion.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "ipr_question")
public class IprQuestionEntity extends SimpleEntity {

    @Column(name = "CODE")
    private String code;

    @Column(name = "IPR_CODE")
    private String iprCode;

    @Column(name = "ORDER_QUESTION")
    private Integer orderQuestion;

    @Column(name = "PERCENTAGE_RESPECT_TO")
    private Integer percentageRespectTo;

    @Column(name = "FORMULA")
    private String formula;

    @Column(name = "QUESTION")
    private String question;

    @ManyToOne
    @JoinColumn(name = "IPR_ID")
    private IprEntity iprId;

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
        IprQuestionEntity that = (IprQuestionEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

    public interface SimpleProjection {

        Long getId();
        String getCode();
        String getIprCode();
        Integer getOrderQuestion();
        Integer getPercentageRespectTo();
        String getFormula();
        String getQuestion();
        IprEntity getIprId();

    }
}


