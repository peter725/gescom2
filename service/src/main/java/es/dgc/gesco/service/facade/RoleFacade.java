package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.role.db.entity.Role;
import es.dgc.gesco.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class RoleFacade {

    @Autowired
    private RoleService roleService;

    public Page<Role> getAllPage(Pageable pageable) {
        Page<Role> rolePage = roleService.getAllPage(pageable);
        return  rolePage;
    }
}
