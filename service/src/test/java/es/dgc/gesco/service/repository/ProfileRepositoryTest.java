package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.profile.db.entity.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileRepositoryTest extends AbstractGenericTest{

    @Autowired
    private ProfileRepository profileRepository;

    //@Test
    public void getAllPageTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Profile> profilePage = profileRepository.findAll(pageable);
        assertEquals(1, profilePage.getTotalElements());

    }
}
