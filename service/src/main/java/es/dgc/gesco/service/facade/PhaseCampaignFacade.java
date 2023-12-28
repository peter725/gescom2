package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.phase.db.entity.PhaseCampaign;
import es.dgc.gesco.service.service.PhaseCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhaseCampaignFacade {

    @Autowired
    private PhaseCampaignService phaseCampaignService;

    public PhaseCampaign getPhaseCampaignById(final Long id) {
        PhaseCampaign phaseCampaign = phaseCampaignService.getPhaseCampaignByid(id);

        return  phaseCampaign;
    }
}