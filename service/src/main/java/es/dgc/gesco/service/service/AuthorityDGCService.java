package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.authorityDGC.db.entity.AuthorityDGC;
import es.dgc.gesco.service.repository.AuthorityDGCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityDGCService {

    @Autowired
    private final AuthorityDGCRepository authorityDGCRepository;

    public AuthorityDGC saveAutonomousCommunity(final AuthorityDGC authorityDGC){

        AuthorityDGC newAuthorityDGC1 = authorityDGCRepository.save(authorityDGC);
        return newAuthorityDGC1;
    }


    public Page<AuthorityDGC> getAllPage(Pageable pageable) {

        Page<AuthorityDGC> authorityDGCPage = authorityDGCRepository.findAll(pageable);
        return authorityDGCPage;
    }


    public List<AuthorityDGC> getAll() {

        return null;
    }
}
