package es.consumo.gescom.modules.arbitration.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.arbitration.model.criteria.ArbitrationCriteria;
import es.consumo.gescom.modules.arbitration.model.dto.AdmissionDTO;
import es.consumo.gescom.modules.arbitration.model.dto.ArbitrationDTO;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.arbitration.model.dto.ReassignDTO;
import es.consumo.gescom.modules.arbitration.model.dto.StatusChangeDTO;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationStatusEntity;
import es.consumo.gescom.modules.arbitration.model.entity.ClaimantEntity;
import es.consumo.gescom.modules.arbitration.model.entity.ClaimedEntity;
import es.consumo.gescom.modules.arbitration.repository.ArbitrationRepository;
import es.consumo.gescom.modules.arbitration.repository.ArbitrationStatusRepository;
import es.consumo.gescom.modules.arbitration.service.ArbitrationService;
import es.consumo.gescom.modules.arbitration.service.RequestStatusService;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import es.consumo.gescom.modules.arbitrationBoard.repository.ArbitrationBoardRepository;
import es.consumo.gescom.modules.history.model.entity.HistoryEntity;
import es.consumo.gescom.modules.history.repository.HistoryRepository;
import es.consumo.gescom.modules.role.model.entity.RoleEntity;
import es.consumo.gescom.modules.role.repository.LoginRepository;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * @author serikat
 */
@Service
public class ArbitrationServiceImpl extends EntityCrudService<ArbitrationEntity, Long> implements ArbitrationService {
    private final CrudService<ClaimantEntity, Long> claimantService;
    private final CrudService<ClaimedEntity, Long> claimedService;

    private final ArbitrationRepository arbitrationRepository;

    private final ArbitrationStatusRepository arbitrationStatusRepository;

    private final RequestStatusService statusService;

    private final ArbitrationBoardRepository arbitrationBoardRepository;

    private final LoginRepository loginRepository;

    private final HistoryRepository historyRepository;

    private static final Long STATUS_INITIAL = 1L;


    @Autowired
    public ArbitrationServiceImpl(GESCOMRepository<ArbitrationEntity, Long> repository,
                                  CrudService<ClaimantEntity, Long> claimantService,
                                  CrudService<ClaimedEntity, Long> claimedService,
                                  ArbitrationRepository arbitrationRepository,
                                  RequestStatusService statusService,
                                  ArbitrationStatusRepository arbitrationStatusRepository,
                                  ArbitrationBoardRepository arbitrationBoardRepository,
                                  LoginRepository loginRepository,
                                  HistoryRepository historyRepository) {
        super(repository);
        this.claimantService = claimantService;
        this.claimedService = claimedService;
        this.arbitrationRepository = arbitrationRepository;
        this.statusService = statusService;
        this.arbitrationStatusRepository = arbitrationStatusRepository;
        this.arbitrationBoardRepository = arbitrationBoardRepository;
        this.loginRepository = loginRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ArbitrationEntity performCreate(ArbitrationEntity payload) {
        ClaimantEntity claimant = claimantService.create(payload.getClaimant());
        ClaimedEntity claimed = claimedService.create(payload.getClaimed());
        payload.setClaimant(claimant);
        payload.setClaimed(claimed);
        payload.setStatus(this.statusService.findById(STATUS_INITIAL).orElseThrow());
        return repository.save(payload);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ArbitrationEntity performUpdate(Long id, ArbitrationEntity payload) {

        ClaimantEntity claimant = claimantService.update(payload.getClaimant().getId(), payload.getClaimant());
        payload.setClaimant(claimant);
        ClaimedEntity claimed = claimedService.update(payload.getClaimed().getId(), payload.getClaimed());
        payload.setClaimed(claimed);

        return repository.save(payload);
    }

    @Override
    public Page<ArbitrationEntity.SimpleProjection> findAllSimpleEntity(CriteriaWrapper<ArbitrationCriteria> wrapper) {
        return arbitrationRepository.findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
    }

    @Override
    public Page<ArbitrationEntity.SimpleProjection> findAllSimpleEntityAndArbitrationBoard(CriteriaWrapper<ArbitrationCriteria> wrapper, LoginEntity login) {
        /* Condición si solo es Solicitante */
        ArbitrationBoardEntity  arbitrationBoardEntity = arbitrationBoardRepository.findById(null).orElseThrow();
        LoginEntity loginEntity = loginRepository.findById(login.getId()).orElseThrow();
        Set<RoleEntity> roles = loginEntity.getRoles();
        if(roles.size() == 1 && roles.stream().anyMatch(role -> role.getId() == 2)){
            return arbitrationRepository.findAllByCriteriaAndArbitrationBoard(wrapper.getCriteria(), wrapper.getCriteria().toPageable(), arbitrationBoardEntity, login.getUsername());
        }else if(roles.stream().anyMatch(role -> role.getId() == 1)) {
            return arbitrationRepository.findAllByCriteria(wrapper.getCriteria(), wrapper.getCriteria().toPageable());
        }else if(roles.stream().anyMatch(role -> role.getId() == 3)){
            return arbitrationRepository.findAllByCriteriaAndArbitrationBoard(wrapper.getCriteria(), wrapper.getCriteria().toPageable(), arbitrationBoardEntity, null);
        }else{
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ArbitrationEntity update(ReassignDTO reassignDTO) {
        LocalDateTime today = LocalDateTime.now();
        ArbitrationEntity arbitrationEntity = arbitrationRepository.findById(reassignDTO.getId())
        .orElseThrow(() -> new EntityNotFoundException("No se encontró registro con ID: " + reassignDTO.getId()));
        ArbitrationStatusEntity statusEntity = arbitrationStatusRepository.findById(4L).orElseThrow();
        arbitrationEntity.setUpdateAt(today);
        arbitrationEntity.setArbitrationBoard(reassignDTO.getArbitrationBoard());
        arbitrationEntity.setCauses(reassignDTO.getCauses());
        arbitrationEntity.setStatus(statusEntity);
        arbitrationRepository.save(arbitrationEntity);

        createHistory(reassignDTO.getCauses(), arbitrationEntity, statusEntity);

        return arbitrationEntity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ArbitrationEntity admission(AdmissionDTO admissionDTO) {
        LocalDateTime today = LocalDateTime.now();
        ArbitrationEntity arbitrationEntity = arbitrationRepository.findById(admissionDTO.getArbitrationId())
        .orElseThrow(() -> new EntityNotFoundException("No se encontró registro con ID: " + admissionDTO.getArbitrationId()));
        ArbitrationStatusEntity statusEntity = arbitrationStatusRepository.findById(admissionDTO.getAdmissionForm().getAdmissionStatus()).orElseThrow();
        arbitrationEntity.setUpdateAt(today);
        arbitrationEntity.setStatus(statusEntity);
        arbitrationEntity.setCauses(admissionDTO.getAdmissionForm().getCauses());
        arbitrationRepository.save(arbitrationEntity);

        createHistory(admissionDTO.getAdmissionForm().getCauses(), arbitrationEntity, statusEntity);

        return arbitrationEntity;
    }

    @Override
    public ArbitrationEntity update(Long id, ArbitrationEntity payload) {
        return super.update(id, payload);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ArbitrationEntity verify(ArbitrationDTO arbitrationDTO) {
        LocalDateTime today = LocalDateTime.now();
        ArbitrationEntity arbitrationEntity = arbitrationRepository.findById(arbitrationDTO.getId()).
        orElseThrow(() -> new EntityNotFoundException("No se encontró registro con ID: " + arbitrationDTO.getId()));
        arbitrationEntity.setUpdateAt(today);
        arbitrationEntity.setCauses(arbitrationDTO.getCauses());
        arbitrationEntity.setStatus(arbitrationDTO.getStatus());
        arbitrationRepository.save(arbitrationEntity);

        createHistory(arbitrationDTO.getCauses(), arbitrationEntity, arbitrationDTO.getStatus());

        return arbitrationEntity;
    }

    @Override
    public StatusChangeDTO changeStatus(LoginEntity loginUser, long requestId, long statusId, Map<String, Object> requestChangeStatus) {
        ArbitrationEntity entity = findById(requestId).orElseThrow();
        return statusService.sendEvent(loginUser, entity, statusId, requestChangeStatus);
    }

    @Override
    @Transactional
    public ArbitrationEntity switchStatus(ChangeStatusDTO changeStatusDTO) {
        ArbitrationEntity entity = findById(changeStatusDTO.getArbitrationId()).orElseThrow();
        entity.setStatus(changeStatusDTO.getStatus());
        entity.setCauses(changeStatusDTO.getCauses());

        createHistory(changeStatusDTO.getCauses(), entity, changeStatusDTO.getStatus());
        return arbitrationRepository.save(entity);
    }

    @Transactional
    public HistoryEntity createHistory(String causes, ArbitrationEntity arbitrationEntity, ArbitrationStatusEntity arbitrationStatusEntity){
        LocalDateTime today = LocalDateTime.now();
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setDescription("Ha sido "+arbitrationStatusEntity.getName()+" por motivo de: "+causes);
        historyEntity.setArbitrationBoard(arbitrationEntity.getArbitrationBoard());
        historyEntity.setArbitration(arbitrationEntity);
        historyEntity.setCreateAt(today);
        historyEntity.setUpdateAt(today);
        return historyRepository.save(historyEntity);
    }
}
