package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.autonomousCommunityParticipants.db.entity.AutonomousCommunityParticipants;
import es.dgc.gesco.service.repository.AutonomousCommunityParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutonomousCommunityParticipantService {


    private final AutonomousCommunityParticipantRepository autonomousCommunityParticipantRepository;


    public AutonomousCommunityParticipants save(final AutonomousCommunityParticipants autonomousCommunityParticipant){
        return autonomousCommunityParticipantRepository.save(autonomousCommunityParticipant);
    }

    public List<AutonomousCommunityParticipants> findByCampaignId(final Long campaignId){
        return autonomousCommunityParticipantRepository.findByCampaignId(campaignId);
    }


}
