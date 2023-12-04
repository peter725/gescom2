package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.role.db.entity.Role;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestFactory.class)
public class RoleServiceTest extends AbstractGenericTest {

    //@Autowired
    //RoleService roleService;
    //
    //@Test
    //public void getAllTest() {
    //    Pageable pageable = PageRequest.of(0, 10);
    //    Page<Role> rolePage = roleService.getAllPage(pageable);
    //    assertEquals(1, rolePage.getTotalElements());
    //}
}
