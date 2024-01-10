package es.consumo.junta_arbitral.modules.arbitration.model.entity;

import es.consumo.junta_arbitral.modules.general.model.entity.RoadTypeEntity;
import es.consumo.junta_arbitral.modules.general.model.entity.TownEntity;
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
@Table(name = "location")
public class LocationEntity extends SimpleEntity {

    @Basic
    @CreatedDate
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @Basic
    @LastModifiedDate
    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;
    @Basic
    @Column(name = "address", nullable = true, length = 200)
    private String address;
    @Basic
    @Column(name = "number", nullable = true)
    private Integer number;
    @Basic
    @Column(name = "block", nullable = true)
    private String block;
    @Basic
    @Column(name = "portal", nullable = true)
    private String portal;
    @Basic
    @Column(name = "ladder", nullable = true)
    private String ladder;
    @Basic
    @Column(name = "floor", nullable = true)
    private String floor;
    @Basic
    @Column(name = "postal_code", nullable = true)
    private String postalCode;
    @Basic
    @Column(name = "door", nullable = true)
    private String door;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "road_type_id", nullable = true)
    private RoadTypeEntity roadType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_id", nullable = true)
    private TownEntity town;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationEntity that = (LocationEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(createAt, that.createAt)
                && Objects.equals(updateAt, that.updateAt) && Objects.equals(address, that.address)
                && Objects.equals(number, that.number) && Objects.equals(block, that.block)
                && Objects.equals(portal, that.portal) && Objects.equals(ladder, that.ladder)
                && Objects.equals(floor, that.floor) && Objects.equals(door, that.door)
                && Objects.equals(getRoadType(), that.getRoadType()) && Objects.equals(getTown(), that.getTown());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), createAt, updateAt, address, number, block, portal, ladder, floor, door, getRoadType(), getTown());
    }
}
