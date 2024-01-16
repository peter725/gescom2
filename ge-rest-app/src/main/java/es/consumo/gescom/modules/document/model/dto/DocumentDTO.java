package es.consumo.gescom.modules.document.model.dto;

import es.consumo.gescom.modules.document.model.entity.DocumentTypeEntity;
import es.consumo.gescom.commons.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO implements Serializable, LongIdModel {

    private Long id;
    private long arbitrationId;
    private String createAt;
    private DocumentTypeEntity documentType;
    private String name;
    private String extension;
    private String base64;
    private String sign;

}
