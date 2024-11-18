package es.consumo.gescom.commons.dto.wrapper;

import lombok.Data;

@Data
public class RequestDataWrapper<T> {
    private final T source;
}
