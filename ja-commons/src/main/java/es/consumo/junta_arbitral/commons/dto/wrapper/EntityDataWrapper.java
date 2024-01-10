package es.consumo.junta_arbitral.commons.dto.wrapper;

import es.consumo.junta_arbitral.commons.db.entity.BaseEntity;

public class EntityDataWrapper<T extends BaseEntity> extends RequestDataWrapper<T> {
    public EntityDataWrapper(T source) {
        super(source);
    }
}
