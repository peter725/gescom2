package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import es.dgc.gesco.service.repository.AuthorityOEURepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityOEUService {

    @Autowired
    private final AuthorityOEURepository authorityOEURepository;

    public Page<AuthorityOEU> getAllPage(Pageable pageable) {

        Page<AuthorityOEU> authorityOEUPage = authorityOEURepository.findAll(pageable);
        return authorityOEUPage;
    }

}