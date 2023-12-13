package es.dgc.gesco.model.commom.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
public class StatusChange {
    @NotNull
    @Min(1)
    private Integer status;
}
