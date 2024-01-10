package es.consumo.junta_arbitral.modules.campaignType.model.converter;

import es.consumo.junta_arbitral.commons.converter.SimpleDataConverter;
import es.consumo.junta_arbitral.modules.campaignType.model.dto.CampaignTypeDTO;
import es.consumo.junta_arbitral.modules.campaignType.model.entity.CampaignTypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



@Service
public class CampaingnTypeConverter extends SimpleDataConverter<CampaignTypeEntity, CampaignTypeDTO> {

    public CampaingnTypeConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
