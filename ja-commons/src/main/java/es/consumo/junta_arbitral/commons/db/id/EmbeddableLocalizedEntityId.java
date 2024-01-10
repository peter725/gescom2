package es.consumo.junta_arbitral.commons.db.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmbeddableLocalizedEntityId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID", nullable = false)
    private Long parentId;

    @Column(name = "ID_LANGUAGE", nullable = false)
    private Long languageId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmbeddableLocalizedEntityId)) return false;
        EmbeddableLocalizedEntityId that = (EmbeddableLocalizedEntityId) o;
        return Objects.equals(getParentId(), that.getParentId()) && Objects.equals(getLanguageId(), that.getLanguageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParentId(), getLanguageId());
    }

}
