package es.dgc.gesco.model.commom.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author serikat
 */
@Entity
@Table(name = "LANGUAGES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Language extends SimpleStatefulEntity {

    public static final Long NO_LANGUAGE = -1L;

    @Column(name = "COD_ISO_639", nullable = false, length = 4)
    @NotNull
    @NotBlank
    private String isoCode;

    @Column(name = "NAME", length = 256)
    @NotNull
    @NotBlank
    private String name;
}

