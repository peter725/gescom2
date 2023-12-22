package es.dgc.gesco.service.service;


import es.dgc.gesco.model.modules.campaignType.db.entity.CampaignType;
import es.dgc.gesco.service.repository.CampaignTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignTypeService {

    @Autowired
    private final CampaignTypeRepository campaignTypeRepository;


    public CampaignType getCampaignTypeById(final Long id){
        CampaignType campaignType = campaignTypeRepository.findById(id).get();
        return campaignType;
    }

    public Page<CampaignType> getAllByPage(Pageable pageable) {
        Page<CampaignType> campaignTypePage = campaignTypeRepository.findAll(pageable);
        return campaignTypePage;
    }
}
