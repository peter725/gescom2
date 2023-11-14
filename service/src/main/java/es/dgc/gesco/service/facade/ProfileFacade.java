package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.profile.db.entity.Profile;
import es.dgc.gesco.service.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProfileFacade {

    @Autowired
    private ProfileService profileService;

    public Page<Profile> getAllPage(Pageable pageable) {
        Page<Profile> profilePage = profileService.getAllPage(pageable);
        return  profilePage;
    }


}
