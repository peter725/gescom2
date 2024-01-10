package es.consumo.junta_arbitral.modules.module.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import es.consumo.junta_arbitral.commons.converter.SimpleDataConverter;
import es.consumo.junta_arbitral.modules.module.model.dto.ModuleDTO;
import es.consumo.junta_arbitral.modules.module.model.entity.ModuleEntity;

@Service
public class ModuleConverter extends SimpleDataConverter<ModuleEntity, ModuleDTO> {

    public ModuleConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
    
}
