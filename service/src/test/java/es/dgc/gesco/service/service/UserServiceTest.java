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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestFactory.class)
public class UserServiceTest extends AbstractGenericTest {

    @Autowired
    UserService userService;

    @Test
    public void getUserByIdTest(){

        UserDto userDto = userService.getUserById(5L);
        assertNotNull(userDto);

    }

    @Test
    public void getAllUsers(){
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setName("jhon");
        Page<User> userPage = userService.getAllByCriteria(userCriteria);
        assertTrue(userPage.getSize()>0);
    }

    @Test
    public void saveUsers(){
        User user = new User();
        user.setName("alexander");
        user.setFirstSurname("maldonado");
        user.setSecondSurname("zambrano");
        user.setEmail("amzambrano@serikat.es");
        user.setPassword("serikat1");
        user.setState(1);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setCreatedBy(1L);
        user.setUpdatedBy(1L);

        user = userService.saveUser(user);

        assertNotNull(user.getId());
    }

    @Test
    public void updateUserTest(){

        String nifEdited = "000000000";

        UserDto userDto = userService.getUserById(1L);

        userDto.setNif(nifEdited);

        userService.updateUser(userDto);

        assertNotNull(userDto);
    }

    @Test
    public void deleteUserTest(){

        userService.deleteUser(1L);
        UserDto userDto = userService.getUserById(1L);
        assertEquals(userDto.getState(),2);
    }

}
















