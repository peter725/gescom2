package es.dgc.gesco.controller;

import es.dgc.gesco.facade.AuthorityOEUFacade;

import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
import es.dgc.gesco.util.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Log4j2
@RestController
@RequestMapping(Url.API+Url.AUTHORITY_OEU)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"}, maxAge = 3600)
public class AuthorityOEUController {

    @Autowired
    private AuthorityOEUFacade authorityOEUFacade;


    @PostMapping(Url.AUTHORITY_OEU)
    public ResponseEntity<Void> findAll() {

        List<AuthorityOEU> authorityOEUS;

        try {

            authorityOEUS = authorityOEUFacade.findAll();

        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
