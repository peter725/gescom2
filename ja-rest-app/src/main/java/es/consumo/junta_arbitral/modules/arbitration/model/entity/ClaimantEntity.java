package es.consumo.junta_arbitral.modules.arbitration.model.entity;


import es.consumo.junta_arbitral.modules.arbitration.model.constants.ClaimantType;
import es.consumo.junta_arbitral.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "claimant")
public class ClaimantEntity extends SimpleEntity {
    @CreatedDate
    @Basic
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Basic
    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @Basic
    @Column(name = "lastname", nullable = true, length = 100)
    private String lastname;
    @Basic
    @Column(name = "dni", nullable = true, length = 100)
    private String dni;
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;
    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;
    @CreatedBy
    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "claimant_type", nullable = true)
//    @Type(type = PostgreSQLEnumType.class)
    @ColumnTransformer(write = "?::claimant_type")
    private ClaimantType claimantType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;
    @OneToOne(mappedBy = "claimant", optional = true, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private RepresentedEntity represented;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClaimantEntity that = (ClaimantEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(createAt, that.createAt)
                && Objects.equals(updateAt, that.updateAt)
                && Objects.equals(email, that.email) && Objects.equals(phone, that.phone)
                && Objects.equals(dni, that.dni)
                && Objects.equals(userId, that.userId) && Objects.equals(getLocation(), that.getLocation())
                && Objects.equals(claimantType, that.claimantType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), createAt, updateAt, email, phone, name, lastname, dni, userId, getLocation(), claimantType);
    }


    public interface SimpleProjection {
        String getName();

        String getLastname();

        String getNif();
    }
}
