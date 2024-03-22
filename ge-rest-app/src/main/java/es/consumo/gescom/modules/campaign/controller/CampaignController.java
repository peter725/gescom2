package es.consumo.gescom.modules.campaign.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.dto.*;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import es.consumo.gescom.modules.excel.ExcelUtils;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.phase.model.dto.PhaseDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.users.model.entity.UserEntity;
import es.consumo.gescom.modules.users.service.UserService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/campaign")
@Tag(name = "Campaign controller")
public class CampaignController extends AbstractCrudController<CampaignEntity, CampaignDTO, Long, CampaignCriteria> {

    @Autowired
    public CampaignController(CampaignService service, DataConverter<CampaignEntity, CampaignDTO> dataConverter) {
        super(service, dataConverter);
    }

    @Override
    protected CampaignDTO performCreate(CampaignDTO payload) {
        return ((CampaignService) service).createCampaign(payload);
    }

    @Override
    protected CampaignDTO performUpdate(Long id, CampaignDTO payload) {
        return  ((CampaignService) service).updateCampaign(id, payload);
    }

    @Override
    protected Optional<?> performFindById(Long id) {

        return Optional.of(
                ((CampaignService) service).findCampaignById(id)
        );
    }


    @PostMapping("/{id}/switch")
    public ResponseEntity<CampaignEntity> switchStatus(@RequestBody ChangeStatusDTO changeStatus, @PathVariable  Long id) {
        CampaignEntity result = ((CampaignService) service).switchStatus(changeStatus, id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/phase")
    public ResponseEntity<CampaignEntity> switchPhase(@RequestBody PhaseDTO changeStatus, @PathVariable  Long id) {
        CampaignEntity result = ((CampaignService) service).switchPhase(changeStatus, id);
        return ResponseEntity.ok(result);
    }

    /*@PostMapping("/results")
    public ResponseEntity<ResultsResponseDTO> getResults(@RequestBody SearchDTO searchDTO) {
        return ResponseEntity.ok(((CampaignService) service).getResults(searchDTO));
    }*/
    
    @PostMapping("/exportExcelProtocolo")
    @Timed
    public byte[] exportTable(@RequestBody ProtocolDTO protocolo)
        throws URISyntaxException, IOException {
        byte[] excel = null;
        boolean falloCreacion = false;
        boolean result = true;

        excel = ExcelUtils.getInstance().createExportExcelTablas(protocolo, result);

        if (null == excel || falloCreacion) {
//            throw new BadRequestAlertException("Fallo en la creacion del export Excel");
        }
        return excel;
    }
    
    @PostMapping("/exportExcelIpr")
    @Timed
    public byte[] exportTable(@RequestBody IprDTO ipr)
        throws URISyntaxException, IOException {
        byte[] excel = null;
        boolean falloCreacion = false;

        excel = ExcelUtils.getInstance().createExportExcelResults(ipr);

        if (null == excel || falloCreacion) {
              //throw new BadRequestAlertException("Fallo en la creacion del export Excel");
        }
        return excel;
    }
}
