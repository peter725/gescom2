package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import es.dgc.gesco.service.repository.AuthorityOEURepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityOEUService {

    @Autowired
    private final AuthorityOEURepository authorityOEURepository;

    public Page<AuthorityOEU> getAllPage(Pageable pageable) {

        Page<AuthorityOEU> authorityOEUPage = authorityOEURepository.findAll(pageable);
        return authorityOEUPage;
    }

    public AuthorityOEU saveAutonomousCommunity(final AuthorityOEU authorityOEU){

        AuthorityOEU newAuthorityOEU1 = authorityOEURepository.save(authorityOEU);
        return newAuthorityOEU1;
    }


    public List<AuthorityOEU> getAll() {

        return null;
    }
}