package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.CampaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.approach.converter.ApproachConverter;
import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.approach.dto.ApproachDto;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.service.service.ApproachService;
import es.dgc.gesco.service.service.AutonomousCommunityService;
import es.dgc.gesco.service.service.CampaignTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApproachFacade {

    @Autowired
    private ApproachService approachService;

    @Autowired
    private ApproachConverter approachConverter;

    @Autowired
    private CampaignTypeService campaignTypeService;

    @Autowired
    private AutonomousCommunityService autonomousCommunityService;


    public Approach saveApproach(final Approach approach){
        Approach newApproach = approachService.saveApproach(approach);

        return newApproach;
    }

    public List<Approach> findAll() {
        List<Approach> approaches = approachService.findAllApproach();
        return approaches;
    }

    public ApproachDto getUserById(final Long id){
        Approach approach = approachService.getApproachById(id);
        ApproachDto approachDto = approachService.loadApproachDto(approach);

        CampaignType campaignType = campaignTypeService.getCampaignTypeById(approach.getCampaignTypeId());
        approachDto.setCampaignTypeName(campaignType.getType());

        return approachDto;
    }

    public Approach loadApproach(ApproachDto approachDto) {
        Approach approach = approachService.loadApproach(approachDto);
        return approach;
    }

    public Page<ApproachDto> getAllApproach(Pageable pageable) {
        Page<ApproachDto> approachDtoPage = null;
        Page<Approach> approachPage = approachService.getAllByPage(pageable);
        approachDtoPage = approachPage.map(approach -> approachConverter.convertApproachToDto(approach));
        approachDtoPage.forEach(approachDto -> {
            CampaignType campaignType = campaignTypeService.getCampaignTypeById(approachDto.getCampaignTypeId());
            AutonomousCommunity autonomousCommunity = autonomousCommunityService.getAutonomousCommunityById(approachDto.getAutonomousCommunityId());
            approachDto.setAutonomousCommunityName(autonomousCommunity.getName());
            approachDto.setCampaignTypeName(campaignType.getType());
        });

        return approachDtoPage;
    }

}