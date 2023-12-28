package es.dgc.gesco.controller;

import es.dgc.gesco.model.modules.phase.db.entity.PhaseCampaign;
import es.dgc.gesco.service.facade.PhaseCampaignFacade;
import es.dgc.gesco.util.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@RestController
@RequestMapping(Url.API+Url.PHASE_CAMPAIGN)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"PhaseCampaign", "Content-Type"}, maxAge = 3600)
public class PhaseCampaignController {

    @Autowired
    private PhaseCampaignFacade phaseCampaignFacade;

    @GetMapping("/{id}")
    public ResponseEntity<PhaseCampaign> getPhaseCampaignById(final @PathVariable Long id) {

        PhaseCampaign phaseCampaign;

        try {

            phaseCampaign = phaseCampaignFacade.getPhaseCampaignById(id);

        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(phaseCampaign);
    }
}