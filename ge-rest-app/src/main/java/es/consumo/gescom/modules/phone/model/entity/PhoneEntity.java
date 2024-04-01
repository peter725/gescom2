package es.consumo.gescom.modules.phone.model.entity;

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
@Table(name = "phone")
@Getter
@Setter
public class PhoneEntity extends SimpleEntity {

    @Column(name = "PHONE" )
    @NotNull(message = "Debes especificar el Teléfono")
    @Size(min = 1, max = 100)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;

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
            PhoneEntity that = (PhoneEntity) o;
            return Objects.equals(getId(), that.getId()) && Objects.equals(phone, that.phone);
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
