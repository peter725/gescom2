package es.consumo.junta_arbitral.commons.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
public class AuditCriteria extends FilterCriteria {

    private Timestamp createdAtLTE;
    private Timestamp createdAtGTE;
    private Timestamp updatedAtLTE;
    private Timestamp updatedAtGTE;
}
