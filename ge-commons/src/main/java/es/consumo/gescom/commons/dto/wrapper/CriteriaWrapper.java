package es.consumo.gescom.commons.dto.wrapper;

import es.consumo.gescom.commons.dto.FilterCriteria;
import lombok.Data;

@Data
public class CriteriaWrapper<T extends FilterCriteria> {
    private final T criteria;
}
