package es.dgc.gesco.controller;

import es.dgc.gesco.model.commom.dto.StatusChange;
import es.dgc.gesco.model.modules.campaign.dto.CampaignDTO;
import es.dgc.gesco.service.facade.CampaignFacade;
import es.dgc.gesco.service.util.Accion;
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
@RequestMapping(Url.API+Url.CAMPAIGN)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Campaign", "Content-Type"}, maxAge = 3600)
public class CampaignController {

    @Autowired
    private CampaignFacade campaignFacade;

    @GetMapping()
    public ResponseEntity<Page<CampaignDTO>> getAllCampaign(@PageableDefault(page = 0, size = 50, sort ="id", direction = Sort.Direction.DESC) final Pageable pageable){
        Page<CampaignDTO> campaignDTOPage;

        try {
            campaignDTOPage = campaignFacade.getAllCampaign(pageable);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(campaignDTOPage);
    }

    @PostMapping(Url.CREATE)
    public ResponseEntity<Void> saveCampaign(final @RequestBody CampaignDTO campaignDto) {


        try {
            campaignFacade.saveCampaign(campaignDto, Accion.ADD);

        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignDTO> getCampaignById(final @PathVariable Long id) {

        CampaignDTO campaignDTO;

        try {

            campaignDTO = campaignFacade.getCampaignById(id);

        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(campaignDTO);
    }

    @PostMapping(Url.CHANGE_STATE+"/{id}"+Url.STATUS)
    public ResponseEntity<CampaignDTO> changeStateUser(final @PathVariable Long id, @RequestBody StatusChange payload) {

        return ResponseEntity.ok(campaignFacade.changeStateCampaign(id, payload));
    }

    @PostMapping(Url.UPDATE+"/{id}")
    public ResponseEntity<String> updateCampaign(@PathVariable Long id, final @RequestBody CampaignDTO campaignDto) {

        campaignFacade.updateCampaign(campaignDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(Url.SEARCH)
    @ResponseBody
    public ResponseEntity<Page<CampaignDTO>> getCampaignByNameOrYearOrCode(
            @RequestParam(name = "nameCampaign", required = false) String nameCampaign,
            @RequestParam(name = "codeCampaign", required = false) String codeCampaign,
            @RequestParam(name = "yearCampaign", required = false) Long yearCampaign,
            @PageableDefault(page = 0, size = 50, sort ="id", direction = Sort.Direction.ASC) final Pageable pageable) {

        Page<CampaignDTO> campaignDTO;

        try {
              campaignDTO = campaignFacade.getCampaignByNameOrYearOrCode(nameCampaign, codeCampaign, yearCampaign, pageable);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(campaignDTO);
    }

    @GetMapping(Url.UPDATE_PHASE+"/{id}")
    public ResponseEntity<String> updatePhaseCampaign(final @PathVariable Long id) {

        campaignFacade.updatePhaseCampaign(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}