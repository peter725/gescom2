package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.autonomousCommunity.converter.AutonomousComunityConverter;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.model.modules.autonomousCommunity.dto.AutonomousComunityDTO;
import es.dgc.gesco.model.modules.user.db.entity.User;
import es.dgc.gesco.service.service.AutonomousCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutonomousCommunityFacade {

    @Autowired
    private AutonomousCommunityService autonomousCommunityService;

    private AutonomousComunityConverter autonomousComunityConverter;

    public Page<AutonomousCommunity> getAllPage (Pageable pageable) {
        Page<AutonomousCommunity> autonomousCommunityPage = autonomousCommunityService.getAllPage(pageable);
        return  autonomousCommunityPage;
    }

    public AutonomousComunityDTO loadAutonomousCommunityDto(AutonomousCommunity autonomousCommunity){
        AutonomousComunityDTO autonomousComunityDTO = autonomousComunityConverter.convertAutonomousCommunityToDto(autonomousCommunity);
        return autonomousComunityDTO;
    }

}
