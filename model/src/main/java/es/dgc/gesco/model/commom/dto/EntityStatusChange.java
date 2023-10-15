package es.dgc.gesco.model.commom.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class EntityStatusChange {
    @NotNull
    @Min(1)
    private Integer status;
}
