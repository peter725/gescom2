package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.userType.db.entity.UserType;
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
public class UserTypeServiceTest extends AbstractGenericTest {

    @Autowired
    UserTypeService userTypeService;

    @Test
    public void getAllPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserType> userTypePage =  userTypeService.getAllUserTypesByPage(pageable);
        assertEquals(1, userTypePage.getTotalElements());
    }

}