package es.consumo.gescom.modules.autonomousCommunityParticipants.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.autonomousCommunity.model.converter.AutonomousCommunityConverter;
import es.consumo.gescom.modules.autonomousCommunity.model.dto.AutonomousCommunityDTO;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
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
    private AutonomousCommunityConverter autonomousCommunityConverter;

    @Override
    public List<AutonomousCommunityDTO> findByIdCampaign(Long idCampaign) {
        List<AutonomousCommunityParticipantsEntity> autonomousCommunityParticipants = autonomousCommunityParticipantsRepository.findByIdCampaign(idCampaign);
        List<AutonomousCommunityDTO> autonomousComunityDTOS = new ArrayList<>();
        autonomousCommunityParticipants.forEach(autonomousCommunityParticipant -> {
            AutonomousCommunityEntity autonomousCommunity = autonomousCommunityParticipant.getAutonomousCommunityEntity();
            AutonomousCommunityDTO dto = autonomousCommunityConverter.convertToModel(autonomousCommunity);
            autonomousComunityDTOS.add(dto);
        });
        Set<AutonomousCommunityDTO> set = new HashSet<>(autonomousComunityDTOS);
        return set.stream().toList();
    }
}
