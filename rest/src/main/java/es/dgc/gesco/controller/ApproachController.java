package es.dgc.gesco.controller;

import es.dgc.gesco.model.commom.dto.StatusChange;
import es.dgc.gesco.model.modules.approach.dto.ApproachDTO;
import es.dgc.gesco.service.facade.ApproachFacade;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@RestController
@RequestMapping(Url.API+Url.APPROACH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Approach", "Content-Type"}, maxAge = 3600)
public class ApproachController {

    @Autowired
    private ApproachFacade approachFacade;

    @PostMapping(Url.CREATE)
    public ResponseEntity<Void> saveApproach(final @RequestBody ApproachDTO approachDto) {

         approachFacade.saveApproach(approachDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @GetMapping
    public ResponseEntity<Page<ApproachDTO>> getAllApproach(@PageableDefault(page = 0, size = 50, sort ="id", direction = Sort.Direction.DESC) final Pageable pageable){
        Page<ApproachDTO> approachDtoPage;

        try {
            approachDtoPage = approachFacade.getAllApproach(pageable);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(approachDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApproachDTO> getProposalById(final @PathVariable Long id) {

        ApproachDTO approachDto;

        try {

            approachDto = approachFacade.getUserById(id);

        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(approachDto);
    }

    @GetMapping(Url.APPROACH_BY_YEAR+"/{year}")
    public ResponseEntity<Page<ApproachDTO>> getProposalByYear(final @PathVariable int year, @PageableDefault(page = 0, size = 50, sort ="id", direction = Sort.Direction.ASC) final Pageable pageable) {

        Page<ApproachDTO> approachDtos;
        try {
            approachDtos = approachFacade.getApproachByDate(year, pageable);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(approachDtos);
    }

    @GetMapping(Url.APPROACH_BY_ACID+"/{id}")
    public ResponseEntity<Page<ApproachDTO>> getProposalByAutonomousCommunityId(final @PathVariable Long id, @PageableDefault(page = 0, size = 50, sort ="id", direction = Sort.Direction.ASC) final Pageable pageable){
        Page<ApproachDTO> approachDtos;

        try {
            approachDtos = approachFacade.getApproachByAutonomousCommunityId(id, pageable);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(approachDtos);
    }

    @PostMapping(Url.CHANGE_STATE+"/{id}"+Url.STATUS)
    public ResponseEntity<ApproachDTO> changeState(@PathVariable Long id, final @RequestBody StatusChange payload) {

        return ResponseEntity.ok(approachFacade.changeStateApproach(id, payload));
    }
}