package es.consumo.gescom.modules.document.model.converter;

import es.consumo.gescom.modules.document.model.dto.DocumentDTO;
import es.consumo.gescom.modules.document.model.entity.DocumentEntity;
import es.consumo.gescom.commons.converter.SimpleDataConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DocumentDataConverter extends SimpleDataConverter<DocumentEntity, DocumentDTO> {


    public DocumentDataConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
