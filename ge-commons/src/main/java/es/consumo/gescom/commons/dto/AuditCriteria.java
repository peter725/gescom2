package es.consumo.gescom.commons.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AuditCriteria extends FilterCriteria {

    private Timestamp createdAtLTE;
    private Timestamp createdAtGTE;
    private Timestamp updatedAtLTE;
    private Timestamp updatedAtGTE;
}
