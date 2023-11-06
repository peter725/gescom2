package es.dgc.gesco.controller;

import es.dgc.gesco.facade.AutonomousCommunityFacade;
import es.dgc.gesco.facade.UserFacade;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.util.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(Url.API+Url.AUTONOMOUS_COMMUNITY)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"}, maxAge = 3600)
public class AutonomousCommunityController {

    @Autowired
    private AutonomousCommunityFacade autonomousCommunityFacade;

    @GetMapping
    public ResponseEntity<Page<AutonomousCommunity>> getAllPage(@PageableDefault(page = 0, size = 25, sort ="id") Pageable pageable){
        Page<AutonomousCommunity> autonomousCommunityPage;

        try {
            autonomousCommunityPage = autonomousCommunityFacade.getAllPage(pageable);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(autonomousCommunityPage);
    }

}
