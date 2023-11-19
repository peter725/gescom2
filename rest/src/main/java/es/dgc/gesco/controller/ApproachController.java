package es.dgc.gesco.controller;

import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.approach.dto.ApproachDto;
import es.dgc.gesco.model.util.exception.Constante;
import es.dgc.gesco.service.facade.ApproachFacade;
import es.dgc.gesco.util.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(Url.API+Url.APPROACH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Approach", "Content-Type"}, maxAge = 3600)
public class ApproachController {

    @Autowired
    private ApproachFacade approachFacade;

    @PostMapping(Url.CREATE)
    public ResponseEntity<Void> saveApproach(final @RequestBody ApproachDto approachDto) {

        Approach approach;

        try {

            approach = approachFacade.loadApproach(approachDto);
            approach.setDate(LocalDate.now());

            approachFacade.saveApproach(approach);

        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constante.REASON,ex);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(Url.APPROACH)
    public ResponseEntity<Void> findAll() {

        List<Approach> approaches;

        try {

            approaches = approachFacade.findAll();

        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApproachDto> getProposalById(final @PathVariable Long id) {

        ApproachDto approachDto;

        try {

            approachDto = approachFacade.getUserById(id);

        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(approachDto);
    }
}