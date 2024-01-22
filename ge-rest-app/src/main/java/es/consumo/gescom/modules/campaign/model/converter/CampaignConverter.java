package es.consumo.gescom.modules.campaign.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.campaign.model.dto.CampaignDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.phase.model.entity.PhaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CampaignConverter extends SimpleDataConverter<CampaignEntity, CampaignDTO> {
    public CampaignConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

 }
