package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.nationalAuthority.db.entity.NationalAuthority;
import es.dgc.gesco.service.repository.NationalAuthorityRepository;
import es.dgc.gesco.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NationalAuthorityService {

    @Autowired
    private final NationalAuthorityRepository nationalAuthorityRepository;

    public List<NationalAuthority> findAllNationalAuthority() {

        List<NationalAuthority> nationalAuthority = nationalAuthorityRepository.findAll();
        return nationalAuthority;
    }
}