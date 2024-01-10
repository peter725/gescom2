package es.consumo.junta_arbitral.modules.users.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;
import es.consumo.junta_arbitral.modules.role.model.entity.RoleEntity;
import es.consumo.junta_arbitral.modules.role.repository.LoginRepository;
import es.consumo.junta_arbitral.modules.role.repository.RoleRepository;
import es.consumo.junta_arbitral.modules.users.model.criteria.UserCriteria;
import es.consumo.junta_arbitral.modules.users.model.dto.UserDTO;
import es.consumo.junta_arbitral.modules.users.model.entity.LoginEntity;
import es.consumo.junta_arbitral.modules.users.model.entity.UserEntity;
import es.consumo.junta_arbitral.modules.users.repository.UserRepository;
import es.consumo.junta_arbitral.modules.users.service.UserService;

@Service
public class UserServiceImpl extends EntityCrudService<UserEntity, Long> implements UserService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(JJAARepository<UserEntity, Long> repository,
                            ModelMapper modelMapper) {
        super(repository);
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<UserEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<UserCriteria> wrapper) {
        return ((UserRepository) repository).findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity create(UserDTO userDTO) {
        
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(userDTO.getDni());
        String password = new BCryptPasswordEncoder().encode("admin");
        loginEntity.setPassword(password);
        loginEntity.setLastAccess(null);
        loginEntity.setEnable(true);
   //     loginEntity.setArbitrationBoard(null);
        RoleEntity roleEntity = roleRepository.findById(1L).orElseThrow();
        Set<RoleEntity> rolesSet = new HashSet<>();
        rolesSet.add(roleEntity);
        loginEntity.setRoles(rolesSet);
        loginRepository.save(loginEntity);

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity.setLogin(loginEntity);
        repository.save(userEntity);
        return userEntity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity update(UserDTO userDTO) {
        UserEntity userEntity = repository.findById(userDTO.getId()).orElseThrow();
        modelMapper.map(userDTO, userEntity);
        repository.save(userEntity);
        return userEntity;
    }

    public UserDTO findByUserId(Long id) {
        UserEntity userEntity = repository.findById(id).orElseThrow();
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        List<LoginEntity> loginEntityList = loginRepository.findByLoginId(id);
        List<RoleEntity> roleList = loginEntityList.stream()
        .flatMap(loginEntity -> loginEntity.getRoles().stream())
        .collect(Collectors.toList());
        userDTO.setRoles(roleList);
        return userDTO;
    }
}
