package es.consumo.gescom.modules.autonomousCommunityParticipants.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.entity.AutonomousCommunityParticipantsEntity;
import es.consumo.gescom.modules.autonomousCommunityParticipants.repository.AutonomousCommunityParticipantsRepository;
import es.consumo.gescom.modules.autonomousCommunityParticipants.service.AutonomousCommunityParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class AutonomousCommunityParticipantsServiceImpl extends EntityCrudService<AutonomousCommunityParticipantsEntity, Long> implements AutonomousCommunityParticipantsService {
    protected AutonomousCommunityParticipantsServiceImpl(GESCOMRepository<AutonomousCommunityParticipantsEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AutonomousCommunityParticipantsRepository autonomousCommunityParticipantsRepository;

}
