package es.consumo.gescom.commons.converter;

import es.consumo.gescom.commons.dto.IdModel;

/**
 * Common converter for the special case when the Entity and the Model are the exact same data type and don't a special
 * mapping implementation.
 * <p>
 * It uses the singleton pattern to avoid unnecessary instances.
 */
public class SameDataConverter<E extends IdModel<?>> implements DataConverter<E, E> {

    private static SameDataConverter<IdModel<?>> instance;

    protected SameDataConverter() {
    }

    @SuppressWarnings("unchecked")
    public static <E extends IdModel<?>> SameDataConverter<E> getInstance() {
        if (instance == null) {
            instance = new SameDataConverter<>();
        }
        return (SameDataConverter<E>) instance;
    }

    @Override
    public E convertToEntity(E entity) {
        return entity;
    }

    @Override
    public void mergeToEntity(E entity, E model) {
    }

    @Override
    public E convertToModel(E entity) {
        return entity;
    }
}
