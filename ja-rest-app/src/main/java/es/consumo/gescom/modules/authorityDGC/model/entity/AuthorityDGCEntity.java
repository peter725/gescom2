package es.consumo.gescom.modules.authorityDGC.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
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
@Entity
@Table(name = "authority_dgc")
@Getter
@Setter
public class AuthorityDGCEntity extends SimpleEntity {

    public static final String SEQ_AUTHORITY_DGC="seq_authority_dgc";

    //genera el campo id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_AUTHORITY_DGC)
    @SequenceGenerator(
            name = SEQ_AUTHORITY_DGC,
            sequenceName = SEQ_AUTHORITY_DGC,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    //genera el campo name string de 50 caracteres
    @Column(name = "NAME" )
    @Size(min = 1, max = 50)
    private String name;

    //genear el campo directionName string de 50 caracteres
    @Column(name = "DIRECTION_NAME" )
    @Size(min = 1, max = 50)
    private String directionName;

    //genera el campo comunidadAutonoma string de 50 caracteres
    @Column(name = "AUTONOMOUS_COMMUNITY" )
    @Size(min = 1, max = 50)
    private String comunidadAutonoma;

    //genera el campo concierge string de 50 caracteres
    @Column(name = "CONCIERGE" )
    @Size(min = 1, max = 50)
    private String concierge;

    //genera el campo addres string de 50 caracteres
    @Column(name = "ADDRESS" )
    @Size(min = 1, max = 50)
    private String address;

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


    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            AuthorityDGCEntity that = (AuthorityDGCEntity) o;
            return Objects.equals(getId(), that.getId()) && Objects.equals(id, that.id);
        }

    @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), getId());
        }

        public interface SimpleProjection {

            Long getId();
            String getName();

        }
}
