package es.consumo.gescom.modules.autonomousCommunityParticipants.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;

import es.consumo.gescom.modules.autonomousCommunityParticipants.model.converter.AutonomousCommunityParticipantsConverter;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.dto.AutonomousCommunityParticipantsDTO;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.entity.AutonomousCommunityParticipantsEntity;
import es.consumo.gescom.modules.autonomousCommunityParticipants.repository.AutonomousCommunityParticipantsRepository;
import es.consumo.gescom.modules.autonomousCommunityParticipants.service.AutonomousCommunityParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AutonomousCommunityParticipantsServiceImpl extends EntityCrudService<AutonomousCommunityParticipantsEntity, Long> implements AutonomousCommunityParticipantsService {
    protected AutonomousCommunityParticipantsServiceImpl(GESCOMRepository<AutonomousCommunityParticipantsEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AutonomousCommunityParticipantsRepository autonomousCommunityParticipantsRepository;

    @Autowired
    private AutonomousCommunityParticipantsConverter autonomousCommunityParticipantsConverter;

    @Override
    public List<AutonomousCommunityParticipantsDTO> findByIdCampaign(Long idCampaign) {
        List<AutonomousCommunityParticipantsEntity> autonomousCommunityParticipants = autonomousCommunityParticipantsRepository.findByIdCampaign(idCampaign);
        List<AutonomousCommunityParticipantsDTO> autonomousComunityDTOS = new ArrayList<>();
        autonomousCommunityParticipants.forEach(autonomousCommunityParticipant -> {
            AutonomousCommunityParticipantsDTO dto = autonomousCommunityParticipantsConverter.convertToModel(autonomousCommunityParticipant);
            autonomousComunityDTOS.add(dto);
        });
        Set<AutonomousCommunityParticipantsDTO> set = new HashSet<>(autonomousComunityDTOS);
        return set.stream().toList();
    }
}
