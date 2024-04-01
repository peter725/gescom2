package es.consumo.gescom.modules.productServices.model.entity;

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
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "product_service")
public class ProductServiceEntity extends SimpleEntity {

    @Column(name = "CODE")
    @Size(min = 1, max = 10)
    private String code;

    @Column(name = "NAME")
    @Size(min = 1, max = 10)
    private String name;

    @Column(name = "BK_NOCO")
    @Size(min = 1, max = 10)
    private String bkNoco;

    @Column(name = "BK_PSDESCRI")
    @Size(min = 1, max = 10)
    private String bkPsdescri;

    @Column(name = "BK_PSINSEL1")
    @Size(min = 1, max = 10)
    private String bkPsinsel1;

    @Column(name = "BK_PSCON0")
    @Size(min = 1, max = 10)
    private String bkPscon0;

    @Column(name = "BK_PSCOICP")
    @Size(min = 1, max = 10)
    private String bkPscoicp;

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
        ProductServiceEntity that = (ProductServiceEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

    public interface SimpleProjection {

        Long getId();
        String getCode();
        String getName();

    }
}


