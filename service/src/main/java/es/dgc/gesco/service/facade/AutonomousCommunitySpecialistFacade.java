package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunitySpecialist;
import es.dgc.gesco.service.service.AutonomousCommunitySpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutonomousCommunitySpecialistFacade {

    private final AutonomousCommunitySpecialistService autonomousCommunitySpecialistService;

    @Autowired
    public AutonomousCommunitySpecialistFacade(AutonomousCommunitySpecialistService autonomousCommunitySpecialistService){
        this.autonomousCommunitySpecialistService = autonomousCommunitySpecialistService;
    }

    public AutonomousCommunitySpecialist save(AutonomousCommunitySpecialist specialist) {
        return autonomousCommunitySpecialistService.save(specialist);
    }
}
