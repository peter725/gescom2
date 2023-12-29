package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.autonomousCommunityParticipants.db.entity.AutonomousCommunityParticipants;
import es.dgc.gesco.model.modules.autonomousCommunitySpecialist.db.entity.AutonomousCommunitySpecialist;
import es.dgc.gesco.service.repository.AutonomousCommunitySpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutonomousCommunitySpecialistService {

    public final AutonomousCommunitySpecialistRepository autonomousCommunitySpecialistRepository;

    public AutonomousCommunitySpecialist save(final AutonomousCommunitySpecialist autonomousCommunitySpecialist){
        return autonomousCommunitySpecialistRepository.save(autonomousCommunitySpecialist);
    }

    public List<AutonomousCommunitySpecialist> findByCampaignId(final Long campaignId){
        return autonomousCommunitySpecialistRepository.findByCampaignId(campaignId);
    }


}
