package es.consumo.junta_arbitral.modules.document.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.junta_arbitral.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "document_type")
public class DocumentTypeEntity extends SimpleEntity {
    @JsonIgnore
    @Basic
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @JsonIgnore
    @Basic
    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "required", nullable = false)
    private Boolean required;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentTypeEntity that = (DocumentTypeEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(createAt, that.createAt) && Objects.equals(updateAt, that.updateAt) && Objects.equals(name, that.name) && Objects.equals(required, that.required);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), createAt, updateAt, name, required);
    }
}
