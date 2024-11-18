package es.consumo.gescom.modules.autonomousCommunityCountry.model.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "autonomous_community_country")
@Getter
@Setter
public class AutonomousCommunityCountryEntity extends SimpleEntity {

    @Column(name = "CODE" )
    @Size(min = 1, max = 50)
    private String code;

    @Column(name = "NAME" )
    @Size(min = 1, max = 50)
    private String name;

    @Column(name = "COUNTRY_CODE" )
    @Size(min = 1, max = 50)
    private String countryCode;

    @Column(name = "COUNTRY_ID" )
    @Size(min = 1, max = 50)
    private Long countryId;

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
            AutonomousCommunityCountryEntity that = (AutonomousCommunityCountryEntity) o;
            return Objects.equals(getName(), that.getName()) && Objects.equals(name, that.name);
        }

    @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), getName());
        }

        public interface SimpleProjection {

            Long getId();
            String getName();

        }
}
