package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.autonomousCommunityParticipants.db.entity.AutonomousCommunityParticipants;
import es.dgc.gesco.model.modules.autonomousCommunityProponent.db.entity.AutonomousCommunityProponent;
import es.dgc.gesco.service.repository.AutonomousCommunityProponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutonomousCommunityProponentService {

    private final AutonomousCommunityProponentRepository autonomousCommunityProponentRepository;

    public AutonomousCommunityProponent save(final AutonomousCommunityProponent autonomousCommunityProponent){
        return autonomousCommunityProponentRepository.save(autonomousCommunityProponent);
    }

    public List<AutonomousCommunityProponent> findByCampaignId(final Long campaignId){
        return autonomousCommunityProponentRepository.findByCampaignId(campaignId);
    }

}
