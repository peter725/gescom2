package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.autonomousCommunity.dto.AutonomousComunityDTO;
import es.dgc.gesco.model.modules.autonomousCommunityParticipants.db.entity.AutonomousCommunityParticipants;
import es.dgc.gesco.model.modules.autonomousCommunityProponent.db.entity.AutonomousCommunityProponent;
import es.dgc.gesco.model.modules.proponent.converter.ProponentConverter;
import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;
import es.dgc.gesco.model.modules.proponent.dto.ProponentDTO;
import es.dgc.gesco.service.service.AutonomousCommunityProponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AutonomousCommunityProponentFacade {

    private final AutonomousCommunityProponentService autonomousCommunityProponentService;

    private final ProponentConverter proponentConverter;

    @Autowired
    public AutonomousCommunityProponentFacade(AutonomousCommunityProponentService autonomousCommunityProponentService, ProponentConverter proponentConverter){
        this.autonomousCommunityProponentService = autonomousCommunityProponentService;
        this.proponentConverter = proponentConverter;
    }

    public AutonomousCommunityProponent save(AutonomousCommunityProponent proponent) {
        return autonomousCommunityProponentService.save(proponent);
    }

    public List<ProponentDTO> findByCampaignId(Long campaignId) {
        List<AutonomousCommunityProponent> autonomousCommunityProponents = autonomousCommunityProponentService.findByCampaignId(campaignId);
        List<ProponentDTO> proponentDTOS = new ArrayList<>();
        autonomousCommunityProponents.forEach(autonomousCommunityProponent -> {
            Proponent proponent = autonomousCommunityProponent.getProponent();
            proponentDTOS.add(proponentConverter.convertProponentToDto(proponent));
        });
        Set<ProponentDTO> set = new HashSet<>(proponentDTOS);
        return set.stream().toList();
    }

}
