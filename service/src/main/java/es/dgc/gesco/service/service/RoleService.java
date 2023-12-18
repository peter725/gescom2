package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.role.db.entity.Role;
import es.dgc.gesco.service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    public Page<Role> getAllPage(Pageable pageable) {

        Page<Role> rolePage = roleRepository.findAll(pageable);
        return rolePage;
    }


}
