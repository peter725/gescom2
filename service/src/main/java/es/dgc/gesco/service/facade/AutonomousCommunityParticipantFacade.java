package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.autonomousCommunity.converter.AutonomousComunityConverter;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.autonomousCommunity.dto.AutonomousComunityDTO;
import es.dgc.gesco.model.modules.autonomousCommunityParticipants.db.entity.AutonomousCommunityParticipants;
import es.dgc.gesco.service.service.AutonomousCommunityParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AutonomousCommunityParticipantFacade {

    private final AutonomousCommunityParticipantService autonomousCommunityParticipantService;

    private final AutonomousComunityConverter autonomousComunityConverter;

    @Autowired
    public AutonomousCommunityParticipantFacade(AutonomousCommunityParticipantService autonomousCommunityParticipantService, AutonomousComunityConverter autonomousComunityConverter){
        this.autonomousCommunityParticipantService = autonomousCommunityParticipantService;
        this.autonomousComunityConverter = autonomousComunityConverter;
    }

    public AutonomousCommunityParticipants save(AutonomousCommunityParticipants participant) {
        return autonomousCommunityParticipantService.save(participant);
    }

    public List<AutonomousComunityDTO> findByCampaignId(Long campaignId) {
        List<AutonomousCommunityParticipants> autonomousCommunityParticipants = autonomousCommunityParticipantService.findByCampaignId(campaignId);
        List<AutonomousComunityDTO> autonomousComunityDTOS = new ArrayList<>();
        autonomousCommunityParticipants.forEach(autonomousCommunityParticipant -> {
            AutonomousCommunity autonomousCommunity = autonomousCommunityParticipant.getAutonomousCommunity();
            autonomousComunityDTOS.add(autonomousComunityConverter.convertAutonomousCommunityToDto(autonomousCommunity));
        });
        Set<AutonomousComunityDTO> set = new HashSet<>(autonomousComunityDTOS);
        return set.stream().toList();
    }
}