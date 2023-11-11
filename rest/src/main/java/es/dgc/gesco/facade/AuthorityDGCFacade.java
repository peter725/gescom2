package es.dgc.gesco.facade;

import es.dgc.gesco.model.modules.authorityDGC.db.entity.AuthorityDGC;
import es.dgc.gesco.service.service.AuthorityDGCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class AuthorityDGCFacade {

    @Autowired
    private AuthorityDGCService authorityDGCService;

    public Page<AuthorityDGC> getAllPage(Pageable pageable) {
        Page<AuthorityDGC> authorityDGCPage = authorityDGCService.getAllPage(pageable);
        return  authorityDGCPage;
    }
}