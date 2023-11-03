package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import es.dgc.gesco.model.modules.nationalAuthority.db.entity.NationalAuthority;
import es.dgc.gesco.service.repository.AuthorityOEURepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityOEUService {

    @Autowired
    private final AuthorityOEURepository authorityOEURepository;

    public List<AuthorityOEU> findAllNationalAuthority() {

        List<AuthorityOEU> authorityOEUList = authorityOEURepository.findAll();
        return authorityOEUList;
    }
}