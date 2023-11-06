package es.dgc.gesco.facade;

import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import es.dgc.gesco.service.service.AuthorityOEUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AuthorityOEUFacade {

    @Autowired
    private AuthorityOEUService authorityOEUService;


    public Page<AuthorityOEU> getAllPage(Pageable pageable) {

        Page<AuthorityOEU> authorityOEUPage = authorityOEUService.getAllPage(pageable);
        return  authorityOEUPage;

    }


}

