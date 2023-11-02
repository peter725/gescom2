package es.dgc.gesco.service.service;

import es.dgc.gesco.service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;
}
