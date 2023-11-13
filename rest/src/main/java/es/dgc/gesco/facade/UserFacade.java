package es.dgc.gesco.facade;


import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.UserDto;
import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import es.dgc.gesco.service.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<User> getAllUser(Pageable pageable){
        Page<User> userPage = userService.getAllByPage(pageable);
        return userPage;
    }

    public UserDto getUserById(final Long id){
        User user = userService.getUserById(id);
        UserDto userDto = userService.loadUserDto(user);
        return userDto;
    }


    public void updateUser(final UserDto userDto){
        User userActual = loadUser(userDto);
        userService.updateUser(userActual);
    }

    public UserDto getUserByNif(final String nif){
        UserDto userDto = userService.getUserByNif(nif);
        return userDto;
    }

    public void changeStateUser(final Long id){
        userService.changeStateUser(id);
    }

    public User loadUser(final UserDto userDto){
        User User = userService.loadUser(userDto);
        return User;
    }


    public List<User> findAll() {
        List<User> user = userService.findAllUser();
        return  user;
    }
}
