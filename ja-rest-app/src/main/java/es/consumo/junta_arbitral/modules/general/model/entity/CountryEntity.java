package es.consumo.junta_arbitral.modules.general.model.entity;

import es.consumo.junta_arbitral.commons.db.entity.SimpleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "country")
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity extends SimpleEntity {

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "iso2", nullable = true, length = 100)
    private String iso2;
    @Basic
    @Column(name = "iso3", nullable = true, length = 100)
    private String iso3;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(name, that.name) && Objects.equals(iso2, that.iso2) && Objects.equals(iso3, that.iso3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, iso2, iso3);
    }
}
