package es.consumo.junta_arbitral.commons.dto.wrapper;

import lombok.Data;

@Data
public class RequestDataWrapper<T> {
    private final T source;
}
