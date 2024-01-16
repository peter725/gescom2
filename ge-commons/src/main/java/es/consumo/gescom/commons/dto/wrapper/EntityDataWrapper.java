package es.consumo.gescom.commons.dto.wrapper;

import es.consumo.gescom.commons.db.entity.BaseEntity;

public class EntityDataWrapper<T extends BaseEntity> extends RequestDataWrapper<T> {
    public EntityDataWrapper(T source) {
        super(source);
    }
}
