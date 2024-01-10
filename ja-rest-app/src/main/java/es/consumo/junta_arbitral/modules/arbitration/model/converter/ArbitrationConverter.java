package es.consumo.junta_arbitral.modules.arbitration.model.converter;

import es.consumo.junta_arbitral.modules.arbitration.model.dto.ArbitrationDTO;
import es.consumo.junta_arbitral.modules.arbitration.model.dto.LocationDTO;
import es.consumo.junta_arbitral.modules.arbitration.model.dto.RepresentedDTO;

import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationStatusEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.LocationEntity;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.RepresentedEntity;
import es.consumo.junta_arbitral.commons.converter.SimpleDataConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArbitrationConverter extends SimpleDataConverter<ArbitrationEntity, ArbitrationDTO> {

    @Autowired
    public ArbitrationConverter(ModelMapper mapper) {
        super(mapper);

        mapper.addMappings(new PropertyMap<LocationEntity, LocationDTO>() {
            protected void configure() {
                map(source.getTown().getProvince(), map().getProvince());
            }
        });

        mapper.addMappings(new PropertyMap<RepresentedDTO, RepresentedEntity>() {
            protected void configure() {
                map(source.getLocation(), map().getLocation());
            }
        });
    }
    @Override
    public void mergeToEntity(ArbitrationEntity entity, ArbitrationDTO model) {
        ArbitrationStatusEntity status = entity.getStatus();
        super.mergeToEntity(entity, model);
        entity.setStatus(status);
    }
}
