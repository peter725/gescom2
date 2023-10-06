package es.dgc.gesco.model.commom.dto.wrapper;

import es.dgc.gesco.model.commom.db.entity.BaseEntity;

public class EntityDataWrapper<T extends BaseEntity> extends RequestDataWrapper<T> {
    public EntityDataWrapper(T source) {
        super(source);
    }
}
