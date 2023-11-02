package es.dgc.gesco.service.repository;

import static org.junit.jupiter.api.Assertions.*;

import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRepositoryTest extends AbstractGenericTest  {

    @Autowired
    UserRepository userRepository;


    //@Test
    public void getAllTest(){
        List<User> userList = userRepository.findAll();
        assertEquals(5, userList.size());
    }

    //@Test
    public void findById(){
        Optional<User> user = userRepository.findById(5L);
        assertNotNull(user.get().getId());
    }

    //@Test
    public void findAllByCriteria(){
        Pageable pageable = PageRequest.of(0, 10, Sort.unsorted());
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setNif("1");
        Page<User> userPage = userRepository.findAllByCriteria(userCriteria, pageable);

        assertTrue(userPage.getSize()>0);
    }


}
