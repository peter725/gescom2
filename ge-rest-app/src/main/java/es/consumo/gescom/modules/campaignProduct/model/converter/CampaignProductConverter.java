package es.consumo.gescom.modules.campaignProduct.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.campaignProduct.model.dto.CampaignProductDTO;
import es.consumo.gescom.modules.campaignProduct.model.entity.CampaignProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CampaignProductConverter extends SimpleDataConverter<CampaignProductEntity, CampaignProductDTO> {
    public CampaignProductConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
