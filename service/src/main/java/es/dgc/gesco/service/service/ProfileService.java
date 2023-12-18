package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.profile.db.entity.Profile;
import es.dgc.gesco.service.repository.AutonomousCommunityRepository;
import es.dgc.gesco.service.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    @Autowired
    private final ProfileRepository profileRepository;

    public Page<Profile> getAllPage(Pageable pageable) {

        Page<Profile> profilePage = profileRepository.findAll(pageable);
        return profilePage;
    }
}
