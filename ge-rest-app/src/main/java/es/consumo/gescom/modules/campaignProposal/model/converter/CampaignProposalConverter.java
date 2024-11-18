package es.consumo.gescom.modules.campaignProposal.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.campaignProposal.model.dto.CampaignProposalDTO;
import es.consumo.gescom.modules.campaignProposal.model.entity.CampaignProposalEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CampaignProposalConverter extends SimpleDataConverter<CampaignProposalEntity, CampaignProposalDTO> {
    public CampaignProposalConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
