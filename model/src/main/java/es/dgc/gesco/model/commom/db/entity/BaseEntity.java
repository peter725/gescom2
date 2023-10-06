package es.dgc.gesco.model.commom.db.entity;

import es.dgc.gesco.model.commom.dto.LongIdModel;

import javax.persistence.MappedSuperclass;

/**
 * Clase abstracta que sirve como definición base para las entidades de la aplicación. No proporciona una definición
 * para el ID común porque no hay una forma
 * <p>
 * La aplicación dispone de varias combinaciones de entidades.
 * - ID numérico (long) generado de forma IDENTITY
 * - ID+Estado: ID numérico (long) generado por IDENTITY y estado
 * - ID+Auditoría: ID numérico (long) generado por SEQUENCE con una definición personalizada
 * - ID+Auditoría+Estado: ID numérico (long) generado por SEQUENCE con una definición personalizada
 * - ID Combinado+Estado: ID numérico (long) compuesto por varias columnas que pueden hacer referencia a otras tablas
 * <p>
 * <p>
 * Estas combinaciones de los identificadores complica la definición de un único campo ID;
 */
@MappedSuperclass
public abstract class BaseEntity implements LongIdModel {

    /**
     * Updates the entity from a source object
     */
    public <S> void update(S source) {
    }
}
