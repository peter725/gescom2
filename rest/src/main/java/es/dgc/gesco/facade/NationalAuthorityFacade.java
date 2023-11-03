package es.dgc.gesco.facade;

import es.dgc.gesco.model.modules.nationalAuthority.db.entity.NationalAuthority;
import es.dgc.gesco.service.service.NationalAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NationalAuthorityFacade {

    @Autowired
    private NationalAuthorityService nationalAuthorityService;

    public List<NationalAuthority> findAll() {

        List<NationalAuthority> nationalAuthorities = nationalAuthorityService.findAllNationalAuthority();
        return  nationalAuthorities;
    }


}

