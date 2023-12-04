package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.role.db.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleRepositoryTest extends AbstractGenericTest{

    //@Autowired
    //private RoleRepository roleRepository;

    //@Test
    //public void getAllTest(){
        //Pageable pageable = PageRequest.of(0, 10);
        //Page<Role> rolePage = roleRepository.findAll(pageable);
        //assertEquals(1, rolePage.getTotalElements());
    //}
}
