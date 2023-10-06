package es.dgc.gesco.model.commom.dto.wrapper;

import lombok.Data;

@Data
public class RequestDataWrapper<T> {
    private final T source;
}
