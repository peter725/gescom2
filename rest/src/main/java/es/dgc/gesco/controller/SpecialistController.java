package es.dgc.gesco.controller;

import es.dgc.gesco.util.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(Url.API+Url.SPECIALIST)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Specialist", "Content-Type"}, maxAge = 3600)
public class SpecialistController {
//
//    @Autowired
//    private SpecialistFacade specialistFacade;
//
//    @GetMapping(Url.ALL)
//    public ResponseEntity<Page<SpecialistDto>> getAllAmbit(@PageableDefault(page = 0, size = 50, sort ="id", direction = Sort.Direction.DESC) final Pageable pageable){
//        Page<SpecialistDto> specialistDtos;
//
//        try {
//            specialistDtos = specialistFacade.getAllSpecialist(pageable);
//        } catch (Exception e) {
//            log.error(e);
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(specialistDtos);
//    }
}