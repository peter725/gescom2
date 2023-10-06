package es.dgc.gesco.service.repository;

import static org.junit.jupiter.api.Assertions.*;

import es.dgc.gesco.model.modules.user.db.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsersRepositoryTest extends AbstractGenericTest  {

    @Autowired
    UserRepository userRepository;


    @Test
    public void saveTest(){
        List<Users> usersList = userRepository.findAll();
        assertEquals(1, usersList.size());
    }


}
