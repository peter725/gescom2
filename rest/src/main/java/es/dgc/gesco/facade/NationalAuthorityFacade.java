package es.dgc.gesco.facade;
import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import es.dgc.gesco.service.service.AuthorityOEUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NationalAuthorityFacade {

    @Autowired
    private AuthorityOEUService authorityOEUService;

    public List<AuthorityOEU> findAll() {

        List<AuthorityOEU> nationalAuthorities = authorityOEUService.findAll();
        return  nationalAuthorities;
    }


}

