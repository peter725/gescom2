package es.dgc.gesco.service.service;


import es.dgc.gesco.model.modules.user.converter.UserConverter;
import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.UserDto;
import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import es.dgc.gesco.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    public User saveUser(final User user){

        User newUser = userRepository.save(user);
        return newUser;
    }

    public UserDto getUserById(Long id){

        Optional<User> user = userRepository.findById(id);
        UserDto userDto = loadUserDto(user.get());
        return userDto;
    }

    public Page<User> getAllByPage(Pageable pageable){

        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    public User updateUser(final UserDto userDto) {

        User userActual = loadUser(userDto);
        return userRepository.save(userActual);
    }

    public void deleteUser(final Long id){

        User user = loadUser(this.getUserById(id));
        user.setState(2);
        userRepository.save(user);
    }

    public UserDto getUserByNif(String nif){

        UserDto userDto = loadUserDto(userRepository.findByNif(nif).get());
        return userDto;
    }

    public List<User> findAllUser(){

        List<User> users = userRepository.findAll();
        return users;
    }

    public User loadUser(final UserDto userDto){

        User user = userConverter.convertDtoToUsuer(userDto);
        return user;
    }

    public UserDto loadUserDto(User user){
        UserDto userDto = userConverter.convertUserToDto(user);
        return userDto;
    }


    public Page<User> getAllByCriteria(UserCriteria userCriteria) {
        return null;
    }

}
