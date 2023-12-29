package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.autonomousCommunity.dto.AutonomousComunityDTO;
import es.dgc.gesco.model.modules.autonomousCommunityParticipants.db.entity.AutonomousCommunityParticipants;
import es.dgc.gesco.model.modules.autonomousCommunitySpecialist.db.entity.AutonomousCommunitySpecialist;
import es.dgc.gesco.model.modules.specialist.converter.SpecialistConverter;
import es.dgc.gesco.model.modules.specialist.db.entity.Specialist;
import es.dgc.gesco.model.modules.specialist.dto.SpecialistDTO;
import es.dgc.gesco.service.service.AutonomousCommunitySpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AutonomousCommunitySpecialistFacade {

    private final AutonomousCommunitySpecialistService autonomousCommunitySpecialistService;

    private final SpecialistConverter specialistConverter;

    @Autowired
    public AutonomousCommunitySpecialistFacade(AutonomousCommunitySpecialistService autonomousCommunitySpecialistService, SpecialistConverter specialistConverter){
        this.autonomousCommunitySpecialistService = autonomousCommunitySpecialistService;
        this.specialistConverter = specialistConverter;
    }

    public AutonomousCommunitySpecialist save(AutonomousCommunitySpecialist specialist) {
        return autonomousCommunitySpecialistService.save(specialist);
    }

    public List<SpecialistDTO> findByCampaignId(Long campaignId) {
        List<AutonomousCommunitySpecialist> autonomousCommunitySpecialists = autonomousCommunitySpecialistService.findByCampaignId(campaignId);
        List<SpecialistDTO> specialistDTOS = new ArrayList<>();
        autonomousCommunitySpecialists.forEach(autonomousCommunitySpecialist -> {
            Specialist specialist = autonomousCommunitySpecialist.getSpecialist();
            specialistDTOS.add(specialistConverter.convertSpecialistToDto(specialist));
        });
        Set<SpecialistDTO> set = new HashSet<>(specialistDTOS);
        return set.stream().toList();
    }
}
