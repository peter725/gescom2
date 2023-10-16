package es.dgc.gesco.controller;

import es.dgc.gesco.facade.UserFacade;
import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.model.modules.user.dto.UserDto;
import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import es.dgc.gesco.util.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@RestController
@RequestMapping(Url.API+Url.USERS)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController{

    @Autowired
    private UserFacade userFacade;

    @PostMapping
    public ResponseEntity<Void> saveUser(final @RequestBody UserDto userDto) {

        User user;

        try {

            user = userFacade.loadUser(userDto);

            user = userFacade.saveUser(user);

        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(final @PathVariable Long id) {

        UserDto userDto;

        try {

            userDto = userFacade.getUserById(id);

        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping(Url.NIF+"/{nif}")
    public ResponseEntity<UserDto> getUserByNif(final @PathVariable String nif) {

        UserDto userDto;

        try {

            userDto = userFacade.getUserByNif(nif);

        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PostMapping(Url.PAGE)
    public ResponseEntity<Page<User>> getAllUser(final @RequestBody UserCriteria criteria){
        Page<User> userPage;

        try {
            userPage = userFacade.getAllUser(criteria);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userPage);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(final @RequestBody UserDto userDto) {

        try {

            userFacade.updateUser(userDto);

        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(final @PathVariable Long id) {

        try {

            userFacade.deleteUser(id);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
