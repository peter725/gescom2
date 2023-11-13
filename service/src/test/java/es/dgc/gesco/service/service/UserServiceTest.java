package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.UserDto;
import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestFactory.class)
public class UserServiceTest extends AbstractGenericTest {

    @Autowired
    UserService userService;

    //@Test
    public void getUserByIdTest(){

        User user = userService.getUserById(5L);
        assertNotNull(user);

    }

    //@Test
    public void getAllUsers(){
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setName("jhon");
        Page<User> userPage = userService.getAllByCriteria(userCriteria);
        assertTrue(userPage.getSize()>0);
    }

    @Test
    public void fidAllUsers(){
       List<User> users = userService.findAllUser();
    }


    public void saveUsers(){
        User user = new User();
        user.setName("alexander");
        user.setFirstSurname("maldonado");
        user.setSecondSurname("zambrano");
        user.setNif("Y5552524A");
        user.setPosition("Position");
        user.setAreaResponsability("Area");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy(1L);
        user.setUpdatedAt(LocalDateTime.now());
        user.setUpdatedBy(1L);


        user = userService.saveUser(user);

        assertNotNull(user.getId());
    }

    //@Test
    public void updateUserTest(){

        String nifEdited = "000000000";

        User user = userService.getUserById(1L);

        user.setNif(nifEdited);

        userService.updateUser(user);

        assertNotNull(user);
    }

    //@Test
    public void deleteUserTest(){

        userService.changeStateUser(1L);
        User user = userService.getUserById(1L);
        assertEquals(user.getState(),2);
    }

}
















