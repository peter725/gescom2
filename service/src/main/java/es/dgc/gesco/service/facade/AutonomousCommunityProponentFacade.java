package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunityProponent;
import es.dgc.gesco.service.service.AutonomousCommunityProponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutonomousCommunityProponentFacade {

    private final AutonomousCommunityProponentService autonomousCommunityProponentService;

    @Autowired
    public AutonomousCommunityProponentFacade(AutonomousCommunityProponentService autonomousCommunityProponentService){
        this.autonomousCommunityProponentService = autonomousCommunityProponentService;
    }

    public AutonomousCommunityProponent save(AutonomousCommunityProponent proponent) {
        return autonomousCommunityProponentService.save(proponent);
    }
}
