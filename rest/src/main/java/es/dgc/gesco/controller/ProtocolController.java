package es.dgc.gesco.controller;

import es.dgc.gesco.model.modules.protocol.dto.ProtocolDTO;
import es.dgc.gesco.service.facade.ProtocolFacade;
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
@RequestMapping(Url.API+Url.PROTOCOL)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = {"Protocol", "Content-Type"}, maxAge = 3600)
public class ProtocolController {

    @Autowired
    private ProtocolFacade protocolFacade;

    @GetMapping(Url.SEARCH)
    public ResponseEntity<Page<ProtocolDTO>> getProtocolByNameOrCode(
            @RequestParam(name = "protocol", required = false) String protocol,
            @RequestParam(name = "code", required = false) String code,
            @PageableDefault(page = 0, size = 50, sort ="id", direction = Sort.Direction.DESC) final Pageable pageable){
        Page<ProtocolDTO> protocolDTOPage;

        try {
            protocolDTOPage = protocolFacade.getProtocolByNameOrCode(protocol, code, pageable);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(protocolDTOPage);
    }
}