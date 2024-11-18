package es.consumo.gescom.commons.db.entity;

/**
 * Allows the implementation of soft-delete entities using a state
 */
public interface StatefulEntity {
    Integer getState();

    void setState(Integer state);
}
