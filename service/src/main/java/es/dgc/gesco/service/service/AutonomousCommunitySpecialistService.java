package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunitySpecialist;
import es.dgc.gesco.service.repository.AutonomousCommunitySpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutonomousCommunitySpecialistService {

    public final AutonomousCommunitySpecialistRepository autonomousCommunitySpecialistRepository;

    public AutonomousCommunitySpecialist save(final AutonomousCommunitySpecialist autonomousCommunitySpecialist){
        AutonomousCommunitySpecialist newAutonomousCommunitySpecialist = autonomousCommunitySpecialistRepository.save(autonomousCommunitySpecialist);
        return newAutonomousCommunitySpecialist;
    }
}
