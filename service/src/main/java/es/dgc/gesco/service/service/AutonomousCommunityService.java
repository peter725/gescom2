package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.service.repository.AutonomousCommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutonomousCommunityService {

    @Autowired
    private final AutonomousCommunityRepository autonomousCommunityRepository;

    public AutonomousCommunity saveAutonomousCommunity(final AutonomousCommunity autonomousCommunity){

        AutonomousCommunity newAutonomousCommunity = autonomousCommunityRepository.save(autonomousCommunity);
        return newAutonomousCommunity;
    }


    public Page<AutonomousCommunity> getAll(Pageable pageable) {

        Page<AutonomousCommunity> autonomousCommunityPage = autonomousCommunityRepository.findAll(pageable);
        return autonomousCommunityPage;
    }


}
