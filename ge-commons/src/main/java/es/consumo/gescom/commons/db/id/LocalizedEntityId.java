package es.consumo.gescom.commons.db.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * Estructura común para las entidades con una PK compuesta por
 * su propio identificador y el idioma relacionado.
 * <p>
 * Clase orientada a ser utilizada junto a una entidad que tiene @IdClass
 *
 * @author serikat
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizedEntityId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long languageId;
}
