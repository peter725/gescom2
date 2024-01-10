package es.consumo.junta_arbitral.modules.arbitration.model.entity;


import es.consumo.junta_arbitral.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "claimed")
public class ClaimedEntity extends SimpleEntity {
    @CreatedDate
    @Basic
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Basic
    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;
    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;
    @Basic
    @Column(name = "social_reason", nullable = true, length = 100)
    private String socialReason;
    @Basic
    @Column(name = "nif", nullable = true)
    private String nif;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClaimedEntity that = (ClaimedEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(createAt, that.createAt)
                && Objects.equals(updateAt, that.updateAt)
                && Objects.equals(email, that.email) && Objects.equals(phone, that.phone)
                && Objects.equals(socialReason, that.socialReason) && Objects.equals(nif, that.nif)
                && Objects.equals(getLocation(), that.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), createAt, updateAt, email, phone, socialReason, nif, getLocation());
    }


}
