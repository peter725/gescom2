package es.consumo.gescom.modules.campaignProductService.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.campaignProductService.model.dto.CampaignProductServiceDTO;
import es.consumo.gescom.modules.campaignProductService.model.entity.CampaignProductServiceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CampaignProductServiceConverter extends SimpleDataConverter<CampaignProductServiceEntity, CampaignProductServiceDTO> {
    public CampaignProductServiceConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
