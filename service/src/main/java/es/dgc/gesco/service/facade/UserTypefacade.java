package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.userType.db.entity.UserType;
import es.dgc.gesco.service.service.UserTypeService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Component
public class UserTypefacade {

    @Autowired
    private UserTypeService userTypeService;

    public void createUserType(UserType userType) {
        userTypeService.save(userType);
    }

    public UserType getUserTypeById(Long id) {
        return userTypeService.getUserTypeById(id);
    }

    public void updateUserType(UserType userType) {
        userTypeService.save(userType);
    }

    public void deleteUserType(Long id) {
        userTypeService.deleteUserType(id);
    }

    public Page<UserType> getAllUserTypesByPage(Pageable pageable) {
        return userTypeService.getAllUserTypesByPage(pageable);
    }
}