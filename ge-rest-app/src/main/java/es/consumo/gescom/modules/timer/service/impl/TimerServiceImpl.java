package es.consumo.gescom.modules.timer.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationStatusEntity;
import es.consumo.gescom.modules.arbitration.repository.ArbitrationRepository;
import es.consumo.gescom.modules.arbitration.repository.ArbitrationStatusRepository;
import es.consumo.gescom.modules.notification.model.entity.NotificationEntity;
import es.consumo.gescom.modules.notification.repository.NotificationRepository;
import es.consumo.gescom.modules.timer.model.dto.CalculateDTO;
import es.consumo.gescom.modules.timer.model.entity.TimerEntity;
import es.consumo.gescom.modules.timer.service.TimerService;

@Service
public class TimerServiceImpl extends EntityCrudService<TimerEntity, Long> implements TimerService  {

    @Autowired
    private final ArbitrationRepository arbitrationRepository;

    @Autowired
    private final ArbitrationStatusRepository arbitrationStatusRepository;

    @Autowired
    private final NotificationRepository notificacionRepository;

    @Autowired
    public TimerServiceImpl(GESCOMRepository<TimerEntity, Long> repository,
                            ArbitrationRepository arbitrationRepository,
                            ArbitrationStatusRepository arbitrationStatusRepository,
                            NotificationRepository notificacionRepository) {
        super(repository);
        this.arbitrationRepository = arbitrationRepository;
        this.arbitrationStatusRepository = arbitrationStatusRepository;
        this.notificacionRepository = notificacionRepository;
    }

    public Integer calculate (CalculateDTO calculateDTO){
        TimerEntity timerEntity = repository.findById(calculateDTO.getTimerId()).orElseThrow();
        ArbitrationEntity arbitration = arbitrationRepository.findById(calculateDTO.getArbitrationId()).orElseThrow();
        LocalDateTime creationDateTime = arbitration.getCreateAt();
        LocalDateTime currentDateTime = LocalDateTime.now();
        long daysDifference = ChronoUnit.DAYS.between(creationDateTime, currentDateTime);
        Integer daysElapsed = Math.toIntExact(daysDifference);
        if(timerEntity.getMinDays() <= daysElapsed){
        List<NotificationEntity> notificationList = notificacionRepository.findByRecipientAndProcessAndArbitrationId(calculateDTO.getProcess(), calculateDTO.getArbitrationId());
        if(notificationList.isEmpty()){
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setArbitrationEntity(arbitration);
            notificationEntity.setCreateAt(currentDateTime);
            notificationEntity.setMessage("Se debe realizar el proceso de "+calculateDTO.getProcess()+" para esta Solicitud");
            notificationEntity.setOrigin("System");
            notificationEntity.setRecipient(arbitration.getClaimant().getName() + " " + arbitration.getClaimant().getLastname());
            notificationEntity.setPriority(1);
            notificationEntity.setStatus(true);
            notificationEntity.setNotificationTypeEntity(arbitration.getNotificationType());
            notificationEntity.setUpdateAt(currentDateTime);
            notificationEntity.setLastSeen(currentDateTime);
            notificationEntity.setProcess(calculateDTO.getProcess());
            notificacionRepository.save(notificationEntity);
        }
        }else if(timerEntity.getMaxDays() <= daysElapsed){
            ArbitrationStatusEntity statusEntity = arbitrationStatusRepository.findById(6L).orElseThrow();
            arbitration.setStatus(statusEntity);
            arbitrationRepository.save(arbitration);
        }
        return daysElapsed;
    }
}
