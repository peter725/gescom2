package es.consumo.gescom.modules.campaign.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.commons.controller.AbstractCrudController;
import es.consumo.gescom.commons.converter.DataConverter;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.campaign.model.criteria.CampaignCriteria;
import es.consumo.gescom.modules.campaign.model.dto.*;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import es.consumo.gescom.modules.excel.ExcelUtils;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.phase.model.dto.PhaseDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import io.micrometer.core.annotation.Timed;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAnyRole;
import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/campaign")
public class CampaignController extends AbstractCrudController<CampaignEntity, CampaignDTO, Long, CampaignCriteria> {

    @Autowired
    public CampaignController(CampaignService service, DataConverter<CampaignEntity, CampaignDTO> dataConverter) {
        super(service, dataConverter);
    }

    // Variable de clase para almacenar los detalles del usuario y los roles
    private UserDetails userDetails;
    private LoginEntity loginEntity;

    // Método para inicializar la autenticación y verificar roles
    private void initializeAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Unauthorized");
        }
        userDetails = (UserDetails) authentication.getPrincipal();
        loginEntity = (LoginEntity) userDetails;
        Hibernate.initialize(loginEntity.getRoles());
    }

    @Override
    public ResponseEntity<Object> findAll(@Valid CampaignCriteria criteria) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_CCAA", "ROLE_CICC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                return super.findAll(criteria);
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        }catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @Override
    public CampaignDTO performCreate(CampaignDTO payload) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                return ((CampaignService) service).createCampaign(payload);
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @Override
    public CampaignDTO performUpdate(Long id, @RequestBody CampaignDTO payload) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                return ((CampaignService) service).updateCampaign(id, payload);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @Override
    public Optional<?> performFindById(Long id) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_CCAA", "ROLE_CICC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                return Optional.of(
                    ((CampaignService) service).findCampaignById(id)
            );
            }else {
                throw new AccessDeniedException("Access Denied");
            }
        }catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @PostMapping("/{id}/switch")
    public ResponseEntity<CampaignEntity> switchStatus(@RequestBody ChangeStatusDTO changeStatus, @PathVariable  Long id) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                CampaignEntity result = ((CampaignService) service).switchStatus(changeStatus, id);
                return ResponseEntity.ok(result);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @PostMapping("/{id}/phase")
    public ResponseEntity<CampaignEntity> switchPhase(@RequestBody PhaseDTO changeStatus, @PathVariable  Long id) {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                CampaignEntity result = ((CampaignService) service).switchPhase(changeStatus, id);
                return ResponseEntity.ok(result);
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }

    }
    
    @PostMapping("/exportExcelProtocolo")
    @Timed
    @RolesAllowed({"DGC", "CCAA", "CICC", "ADMINISTRADOR DE ÁMBITO"})
    public byte[] exportTable(@RequestBody ProtocolDTO protocolo) throws URISyntaxException, IOException {
        try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_CCAA", "ROLE_CICC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                byte[] excel = null;
                boolean falloCreacion = false;
                boolean result = true;

                excel = ExcelUtils.getInstance().createExportExcelTablas(protocolo, result);

                if (null == excel || falloCreacion) {
                    //            throw new BadRequestAlertException("Fallo en la creacion del export Excel");
                }
                return excel;
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }
    
    @PostMapping("/exportExcelIpr")
    @Timed
    public byte[] exportTable(@RequestBody IprDTO ipr) throws URISyntaxException, IOException {
         try {
            initializeAuthentication();
            if (hasAnyRole(userDetails,"ROLE_DGC", "ROLE_CCAA", "ROLE_CICC", "ROLE_ADMINISTRADOR DE ÁMBITO" )) {
                byte[] excel = null;
                boolean falloCreacion = false;

                excel = ExcelUtils.getInstance().createExportExcelResults(ipr);

                if (null == excel || falloCreacion) {
                      //throw new BadRequestAlertException("Fallo en la creacion del export Excel");
                }
                return excel;
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    private boolean hasAnyRole(UserDetails userDetails, String... roles) {
        for (String role : roles) {
        if (userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role))) {
            return true;
        }
    }
        return false;
    }
}
