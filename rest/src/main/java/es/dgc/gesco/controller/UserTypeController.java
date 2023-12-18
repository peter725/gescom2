package es.dgc.gesco.controller;

import es.dgc.gesco.model.modules.userType.db.entity.UserType;
import es.dgc.gesco.service.facade.UserTypefacade;
import es.dgc.gesco.util.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(Url.API+Url.USER_TYPE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"}, maxAge = 3600)
public class UserTypeController {

    @Autowired
    private UserTypefacade userTypefacade;

    @GetMapping
    public ResponseEntity<Page<UserType>> getAllUserTypesByPage(@PageableDefault(page = 0, size = 25, sort ="id") Pageable pageable) {
        try {
            return ResponseEntity.ok(userTypefacade.getAllUserTypesByPage(pageable));
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}