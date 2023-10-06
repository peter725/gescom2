package es.dgc.gesco.service.service;

import es.dgc.gesco.service.util.TestFactory;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import(TestFactory.class)
public class UsersServiceTest extends AbstractGenericTest {

    @Autowired
    UserService userService;

    @Test
    public void addTest(){
        userService.add("Mensaje");
    }

}
