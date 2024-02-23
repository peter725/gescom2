package es.consumo.gescom.modules.ipr.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.ambit.model.dto.AmbitDTO;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AmbitConverter extends SimpleDataConverter<AmbitEntity, AmbitDTO> {
    public AmbitConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
