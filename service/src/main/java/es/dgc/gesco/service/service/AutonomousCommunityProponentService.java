package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunityProponent;
import es.dgc.gesco.service.repository.AutonomousCommunityProponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutonomousCommunityProponentService {

    private final AutonomousCommunityProponentRepository autonomousCommunityProponentRepository;

    public AutonomousCommunityProponent save(final AutonomousCommunityProponent autonomousCommunityProponent){
        AutonomousCommunityProponent newAutonomousCommunityProponent = autonomousCommunityProponentRepository.save(autonomousCommunityProponent);
        return newAutonomousCommunityProponent;
    }

}
