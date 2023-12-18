package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.userType.db.entity.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTypeRepositoryTest extends AbstractGenericTest{

    @Autowired
    UserTypeRepository userTypeRepository;

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserType> userTypePage = userTypeRepository.findAll(pageable);
        assertEquals(1, userTypePage.getTotalElements());
    }
}
