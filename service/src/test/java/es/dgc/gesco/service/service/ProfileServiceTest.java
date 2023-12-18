package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.profile.db.entity.Profile;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestFactory.class)
public class ProfileServiceTest extends AbstractGenericTest {

    @Autowired
    private ProfileService profileService;

    //@Test
    public void getAllPageTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Profile> profilePage = profileService.getAllPage(pageable);
        assertEquals(1, profilePage.getTotalElements());
    }

}
