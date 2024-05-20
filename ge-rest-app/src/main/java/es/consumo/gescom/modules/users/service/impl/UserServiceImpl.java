package es.consumo.gescom.modules.users.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.service.AutonomousCommunityService;
import es.consumo.gescom.modules.profile.service.ProfileService;
import es.consumo.gescom.modules.userType.model.entity.UserTypeEntity;
import es.consumo.gescom.modules.userType.service.UserTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;
import es.consumo.gescom.modules.role.repository.LoginRepository;
import es.consumo.gescom.modules.role.repository.RoleRepository;
import es.consumo.gescom.modules.users.model.criteria.UserCriteria;
import es.consumo.gescom.modules.users.model.dto.UserDTO;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import es.consumo.gescom.modules.users.model.entity.UserEntity;
import es.consumo.gescom.modules.users.repository.UserRepository;
import es.consumo.gescom.modules.users.service.UserService;

@Service
public class UserServiceImpl extends EntityCrudService<UserEntity, Long> implements UserService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private AutonomousCommunityService autonomousCommunityService;

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    public UserServiceImpl(GESCOMRepository<UserEntity, Long> repository,
                           ModelMapper modelMapper) {
        super(repository);
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<UserDTO> findAllUsers(CriteriaWrapper<?> criteriaWrapper) {
        Page<UserDTO> response = repository.findAll(criteriaWrapper.getCriteria().toPageable())
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class));
        return response;
    }

    @Override
    public UserDTO findByUserId(Long id) {
        return null;
    }


    @Override
    protected Page<UserEntity.SimpleProjection>  findAllFromCriteria(FilterCriteria criteria) {

        UserCriteria userCriteria = (UserCriteria) criteria;
        if (userCriteria.getSearch() != null) {
            userCriteria.setSearch(userCriteria.getSearch().toUpperCase());
        }
        if (userCriteria.getName() != null) {
            userCriteria.setName(userCriteria.getName().toUpperCase());
        }
        if (userCriteria.getSurname() != null) {
            userCriteria.setSurname(userCriteria.getSurname().toUpperCase());
        }
        if (userCriteria.getLastSurname() != null) {
            userCriteria.setLastSurname(userCriteria.getLastSurname().toUpperCase());
        }
        if (userCriteria.getDni() != null) {
            userCriteria.setDni(userCriteria.getDni().toUpperCase());
        }
        if (userCriteria.getPhone() != null) {
            userCriteria.setPhone(userCriteria.getPhone().toUpperCase());
        }
        if (userCriteria.getEmail() != null) {
            userCriteria.setEmail(userCriteria.getEmail().toUpperCase());
        }
        userCriteria.setSort(new String[]{"id;desc"});
        Page<UserEntity.SimpleProjection>  userSimpleProjections = ((UserRepository) repository).findAllByCriteria(userCriteria, userCriteria.toPageable());

        return userSimpleProjections;
    }

    @Override
    public Page<UserEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<UserCriteria> wrapper) {
        return ((UserRepository) repository).findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity create(UserDTO userDTO) throws Exception {
        // Verificar si ya existe un usuario con el mismo DNI/NIF
        Optional<LoginEntity> existingLogin = loginRepository.findByUsername(userDTO.getDni());
        if (existingLogin.isPresent()) {
            throw new Exception("Un usuario con este DNI/NIF ya existe.");
        }


        // Creación de la entidad Login
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(userDTO.getDni().replace(" ", "").trim());
        String password = new BCryptPasswordEncoder().encode("admin");
        loginEntity.setPassword(password);
        loginEntity.setLastAccess(null);
        loginEntity.setEnable(true);
        // loginEntity.setArbitrationBoard(null);

        RoleEntity roleEntity = roleRepository.findById(userDTO.getRole().getId()).orElseThrow();
        Set<RoleEntity> rolesSet = new HashSet<>();
        rolesSet.add(roleEntity);
        loginEntity.setRoles(rolesSet);
        loginRepository.save(loginEntity);

        // Creación de la entidad User
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity.setDni(userEntity.getDni().replace(" ","").trim());
        userEntity.setLogin(loginEntity);
        userEntity.setRole(roleEntity);
        repository.save(userEntity);

        return userEntity;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity update(UserDTO userDTO) {

        LoginEntity loginEntity = new LoginEntity();
        UserEntity userEntity = repository.findById(userDTO.getId()).orElseThrow();
        RoleEntity roleEntity = roleRepository.findById(userDTO.getRole().getId()).orElseThrow();
        if (userEntity.getLogin() == null){
            //Creación de la entidad Login
            loginEntity.setUsername(userDTO.getDni());
            String password = new BCryptPasswordEncoder().encode("admin");
            loginEntity.setPassword(password);
            loginEntity.setLastAccess(null);
            loginEntity.setEnable(true);
            Set<RoleEntity> rolesSet = new HashSet<>();
            rolesSet.add(roleEntity);
            loginEntity.setRoles(rolesSet);
            loginRepository.save(loginEntity);
        }else {
            loginEntity = userEntity.getLogin();
            Set<RoleEntity> rolesSet = new HashSet<>();
            rolesSet.add(roleEntity);
            loginEntity.setRoles(rolesSet);
            loginRepository.save(loginEntity);
        }
        //modelMapper.map(userDTO, userEntity);
        AutonomousCommunityEntity autonomousCommunityEntity = autonomousCommunityService.findById(userDTO.getAutonomousCommunity().getId()).orElseThrow();
        UserTypeEntity userTypeEntity = userTypeService.findById(userDTO.getUserType().getId()).orElseThrow();
        //ProfileEntity profileEntity = profileService.findById(userDTO.getProfile().getId()).orElseThrow();
        userEntity.setAutonomousCommunity(autonomousCommunityEntity);
        userEntity.setUserType(userTypeEntity);
        //userEntity.setProfile(profileEntity);
        userEntity.setLogin(userEntity.getLogin());
        userEntity.setState(userDTO.getState());
        userEntity.setDni(userDTO.getDni());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setLastSurname(userDTO.getLastSurname());
        userEntity.setName(userDTO.getName());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setSurname(userDTO.getSurname());
        userEntity.setRole(roleEntity);
        userEntity.setLogin(loginEntity);
        repository.save(userEntity);

        return userEntity;
    }

    /*public UserDTO findByUserId(Long id) {
        UserEntity userEntity = repository.findById(id).orElseThrow();
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        List<LoginEntity> loginEntityList = loginRepository.findByLoginId(id);
        List<RoleEntity> roleList = loginEntityList.stream()
        .flatMap(loginEntity -> loginEntity.getRoles().stream())
        .collect(Collectors.toList());
        //userDTO.setRoles(roleList);
        return userDTO;
    }*/

    @Override
    @Transactional
    public UserEntity switchStatus(ChangeStatusDTO changeStatusDTO, Long id) {
        UserEntity entity = findById(id).orElseThrow();
        entity.setState(changeStatusDTO.getStatus());

        return repository.save(entity);
    }
}
