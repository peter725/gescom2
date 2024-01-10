package es.consumo.junta_arbitral.commons.dto.wrapper;

import es.consumo.junta_arbitral.commons.dto.FilterCriteria;
import lombok.Data;

@Data
public class CriteriaWrapper<T extends FilterCriteria> {
    private final T criteria;
}
