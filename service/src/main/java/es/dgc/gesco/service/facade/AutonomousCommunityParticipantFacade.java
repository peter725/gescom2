package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunityParticipants;
import es.dgc.gesco.service.service.AutonomousCommunityParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutonomousCommunityParticipantFacade {

    private final AutonomousCommunityParticipantService autonomousCommunityParticipantService;

    @Autowired
    public AutonomousCommunityParticipantFacade(AutonomousCommunityParticipantService autonomousCommunityParticipantService){
        this.autonomousCommunityParticipantService = autonomousCommunityParticipantService;
    }

    public AutonomousCommunityParticipants save(AutonomousCommunityParticipants participant) {
        return autonomousCommunityParticipantService.save(participant);
    }
}