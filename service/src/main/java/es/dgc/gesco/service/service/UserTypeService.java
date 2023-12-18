package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.userType.db.entity.UserType;
import es.dgc.gesco.service.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeService {

    @Autowired
    private final UserTypeRepository userTypeRepository;

    public UserType save(UserType userType) {
        return userTypeRepository.save(userType);
    }

    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    public UserType updateUserType(UserType userType) {
        return userTypeRepository.save(userType);
    }

    public void deleteUserType(Long id) {
        userTypeRepository.deleteById(id);
    }

    public Page<UserType> getAllUserTypesByPage(Pageable pageable){
        return userTypeRepository.findAll(pageable);
    }

    public UserType getUserTypeById(Long id) {
        return userTypeRepository.findById(id).get();
    }
}