package es.dgc.gesco.controller;

import es.dgc.gesco.service.facade.AuthorityOEUFacade;

import es.dgc.gesco.model.modules.authorityOEU.db.entity.AuthorityOEU;
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


@Log4j2
@RestController
@RequestMapping(Url.API+Url.AUTHORITY_OEU)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"}, maxAge = 3600)
public class AuthorityOEUController {

    @Autowired
    private AuthorityOEUFacade authorityOEUFacade;


    @GetMapping
    public ResponseEntity<Void> getAllPage(@PageableDefault(page = 0, size = 25, sort ="id") Pageable pageable) {

        Page<AuthorityOEU> authorityOEUPage;

        try {

            authorityOEUPage = authorityOEUFacade.getAllPage(pageable);


        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
