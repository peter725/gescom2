package es.dgc.gesco.controller;


import es.dgc.gesco.model.modules.specialist.dto.SpecialistDTO;
import es.dgc.gesco.service.facade.SpecialistFacade;
import es.dgc.gesco.util.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@RestController
@RequestMapping(Url.API+Url.SPECIALIST)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Specialist", "Content-Type"}, maxAge = 3600)
public class SpecialistController {

    @Autowired
    private SpecialistFacade specialistFacade;

    @GetMapping(Url.ALL)
    public ResponseEntity<Page<SpecialistDTO>> getAllAmbit(@PageableDefault(page = 0, size = 50, sort ="id", direction = Sort.Direction.DESC) final Pageable pageable){
        Page<SpecialistDTO> specialistDtos;

        try {
            specialistDtos = specialistFacade.getAllSpecialist(pageable);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(specialistDtos);
    }
}