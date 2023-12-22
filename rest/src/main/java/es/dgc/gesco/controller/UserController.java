package es.dgc.gesco.controller;

import es.dgc.gesco.model.commom.dto.StatusChange;
import es.dgc.gesco.model.modules.user.dto.UserDTO;
import es.dgc.gesco.service.facade.UserFacade;
import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.service.util.Accion;
import es.dgc.gesco.util.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(Url.API+Url.USERS)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"}, maxAge = 3600)
public class UserController{

    @Autowired
    private UserFacade userFacade;

    @PostMapping(Url.CREATE)
    public ResponseEntity<Void> saveUser(final @RequestBody UserDTO userDto) {

        User user;

        try {

            user = userFacade.loadUser(userDto,Accion.ADD);

            user = userFacade.saveUser(user);

        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(Url.USERS)
    public ResponseEntity<Void> findAll() {

        List<User> user;

        try {

            user = userFacade.findAll();

        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(final @PathVariable Long id) {

        UserDTO userDto;

        try {

            userDto = userFacade.getUserById(id);

        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }


    @GetMapping
    public ResponseEntity<Page<User>> getAllUser(@PageableDefault(page = 0, size = 25, sort ="id") final Pageable pageable){
        Page<User> userPage;

        try {
            userPage = userFacade.getAllUser(pageable);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userPage);
    }

    @PostMapping(Url.UPDATE+"/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, final @RequestBody UserDTO userDto) {

        userFacade.updateUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(Url.CHANGE_STATE+"/{id}"+Url.STATUS)
    public ResponseEntity<UserDTO> changeStateUser(final @PathVariable Long id, @RequestBody StatusChange payload) {

         return ResponseEntity.ok(userFacade.changeStateUser(id, payload));
    }


}
