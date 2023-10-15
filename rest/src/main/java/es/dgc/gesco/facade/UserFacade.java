package es.dgc.gesco.facade;


import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.UserDto;
import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import es.dgc.gesco.service.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserFacade {

    @Autowired
    private UserService userService;

    @Transactional
    public User saveUser(final User user){
        User newUser = userService.saveUser(user);

        return newUser;
    }

    public Page<User> getAllUser(UserCriteria criteria){
        Page<User> userPage = userService.getAllByCriteria(criteria);
        return userPage;
    }

    public UserDto getUserById(final Long id){
        UserDto userDto = userService.getUserById(id);
        return userDto;
    }

    @Transactional
    public void updateUser(final UserDto userDto){
        userService.updateUser(userDto);
    }

    public UserDto getUserByNif(final String nif){
        UserDto userDto = userService.getUserByNif(nif);
        return userDto;
    }

    public void deleteUser(final Long id){
        userService.deleteUser(id);
    }

    public User loadUser(final UserDto userDto){
        User User = userService.loadUser(userDto);
        return User;
    }




}
