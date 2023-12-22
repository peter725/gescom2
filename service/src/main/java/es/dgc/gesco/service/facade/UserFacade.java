package es.dgc.gesco.service.facade;


import es.dgc.gesco.model.commom.dto.StatusChange;
import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.UserDTO;
import es.dgc.gesco.service.service.UserService;
import javax.transaction.Transactional;

import es.dgc.gesco.service.util.Accion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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

    public UserDTO getUserById(final Long id){
        User user = userService.getUserById(id);
        UserDTO userDto = userService.loadUserDto(user);
        return userDto;
    }

    @Transactional
    public void updateUser(final UserDTO userDto){

        valid(userDto, Accion.UPDATE);
        User user = loadUser(userDto, Accion.UPDATE);
        userService.updateUser(user);
    }

    public UserDTO changeStateUser(final Long id, final StatusChange statusChange){
        User user = userService.changeStateUser(id, statusChange.getStatus());
        UserDTO userDto = userService.loadUserDto(user);

        return userDto;
    }

    public User loadUser(final UserDTO userDto, Accion accion){
        User user = userService.loadUser(userDto);
        if (accion.equals(Accion.UPDATE)) {
            User userActual = userService.getUserById(userDto.getId());
            user.setCreatedAt(userActual.getCreatedAt());
            user.setUpdatedAt(LocalDateTime.now());
        }
        return user;
    }


    public List<User> findAll() {
        List<User> user = userService.findAllUser();
        return  user;
    }

    private void valid(final UserDTO userDto, final Accion accion){

        if (accion.equals(Accion.ADD)) {

            Optional<User> user = userService.getByNif(userDto.getNif());
            user.ifPresent((value)->{throw new ResponseStatusException(HttpStatus.FOUND);});

        } else if (accion.equals(Accion.UPDATE)) {

            if (ObjectUtils.isEmpty(userDto.getId()))
                throw new ResponseStatusException(HttpStatus.CONFLICT);

        }

    }
}
