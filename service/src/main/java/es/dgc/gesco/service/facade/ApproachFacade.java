package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.commom.dto.StatusChange;
import es.dgc.gesco.model.modules.approach.dto.ApproachDTO;
import es.dgc.gesco.model.modules.campaignType.db.entity.CampaignType;
import es.dgc.gesco.model.modules.approach.converter.ApproachConverter;
import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.autonomousCommunity.db.entity.AutonomousCommunity;
import es.dgc.gesco.service.service.ApproachService;
import es.dgc.gesco.service.service.AutonomousCommunityService;
import es.dgc.gesco.service.service.CampaignTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;

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


    @Transactional
    public void saveApproach(final ApproachDTO approachDto){
        Approach approach = approachService.loadApproach(approachDto);
        approach.setDate(LocalDate.now());
        approach.setSent(true);
        approach.setUserId(209L);
        approach.setAutonomousCommunityId(20L);
        approachService.saveApproach(approach);

    }

    public ApproachDTO changeStateApproach(final Long id, final StatusChange statusChange){
        Approach approach = approachService.changeStateApproach(id, statusChange.getStatus());
        ApproachDTO approachDto = approachService.loadApproachDto(approach);

        CampaignType campaignType = campaignTypeService.getCampaignTypeById(approach.getCampaignTypeId());
        approachDto.setCampaignTypeName(campaignType.getName());

        return approachDto;
    }


    public ApproachDTO getUserById(final Long id){
        Approach approach = approachService.getApproachById(id);
        ApproachDTO approachDto = approachService.loadApproachDto(approach);

        CampaignType campaignType = campaignTypeService.getCampaignTypeById(approach.getCampaignTypeId());
        approachDto.setCampaignTypeName(campaignType.getName());

        return approachDto;
    }

    public Approach loadApproach(ApproachDTO approachDto) {
        Approach approach = approachService.loadApproach(approachDto);
        return approach;
    }

    public Page<ApproachDTO> getAllApproach(Pageable pageable) {
        Page<Approach> approachPage = approachService.getAllByPage(pageable);

        return  loadApproachPageDto(approachPage);
    }

    public Page<ApproachDTO> getApproachByAutonomousCommunityId(Long id, Pageable pageable) {
        Page<Approach> approachPage = approachService.getApproachByAutonomousCommunityId(id, pageable);

        return  loadApproachPageDto(approachPage);
    }

    public Page<ApproachDTO> getApproachByDate(int year, Pageable pageable) {
        LocalDate localDateIni = LocalDate.ofYearDay(year, 1);
        LocalDate localDateFin = LocalDate.ofYearDay(year, LocalDate.of(year, 12, 31).getDayOfYear());

        Page<Approach> approachPage = approachService.getApproachByDate(localDateIni, localDateFin, pageable);

        return  loadApproachPageDto(approachPage);
    }

    public Page<ApproachDTO> loadApproachPageDto(Page<Approach> approachPage) {
        Page<ApproachDTO> approachDtoPage = approachPage.map(approach -> approachConverter.convertApproachToDto(approach));
        approachDtoPage.forEach(approachDto -> {
            CampaignType campaignType = campaignTypeService.getCampaignTypeById(approachDto.getCampaignTypeId());
            AutonomousCommunity autonomousCommunity = autonomousCommunityService.getAutonomousCommunityById(approachDto.getAutonomousCommunityId());
            approachDto.setAutonomousCommunityName(autonomousCommunity.getName());
            approachDto.setCampaignTypeName(campaignType.getName());
        });
        return approachDtoPage;
    }
}