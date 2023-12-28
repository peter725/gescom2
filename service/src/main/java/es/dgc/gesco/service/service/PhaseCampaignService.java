package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.phase.db.entity.PhaseCampaign;
import es.dgc.gesco.service.repository.PhaseCampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhaseCampaignService {

    @Autowired
    private final PhaseCampaignRepository phaseCampaignRepository;

    public PhaseCampaign getPhaseCampaignByid(Long id) {
        Optional<PhaseCampaign> phaseCampaignOptional = phaseCampaignRepository.findById(id);
        return phaseCampaignOptional.get();
    }

}