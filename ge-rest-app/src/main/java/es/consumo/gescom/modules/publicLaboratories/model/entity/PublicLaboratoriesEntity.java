package es.consumo.gescom.modules.publicLaboratories.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import es.consumo.gescom.modules.users.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "public_laboratories")
@Getter
@Setter
public class PublicLaboratoriesEntity extends SimpleEntity {

    public static final String SEQ_PUBLIC_LABORATORIES="seq_public_laboratories";
    //genera el campo id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_PUBLIC_LABORATORIES)
    @SequenceGenerator(
            name = SEQ_PUBLIC_LABORATORIES,
            sequenceName = SEQ_PUBLIC_LABORATORIES,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    //genera el campo name
    @Column(name = "NAME" )
    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 100)
    private String name;


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
            PublicLaboratoriesEntity that = (PublicLaboratoriesEntity) o;
            return Objects.equals(getId(), that.getId()) && Objects.equals(id, that.id);
        }

    @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), getId());
        }

        public interface SimpleProjection {

            Long getId();
            String getPhone();

        }
}
