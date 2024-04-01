package es.consumo.gescom.modules.autonomousCommunitySpecialist.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.repository.AutonomousCommunityRepository;
import es.consumo.gescom.modules.autonomousCommunitySpecialist.model.criteria.AutonomousCommunitySpecialistCriteria;
import es.consumo.gescom.modules.autonomousCommunitySpecialist.model.entity.AutonomousCommunitySpecialistEntity;
import es.consumo.gescom.modules.autonomousCommunitySpecialist.repository.AutonomousCommunitySpecialistRepository;
import es.consumo.gescom.modules.autonomousCommunitySpecialist.service.AutonomousCommunitySpecialistService;
import es.consumo.gescom.modules.specialist.model.converter.SpecialistConverter;
import es.consumo.gescom.modules.specialist.model.dto.SpecialistDTO;
import es.consumo.gescom.modules.specialist.model.entity.SpecialistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AutonomousCommunitySpecialistServiceImpl extends EntityCrudService<AutonomousCommunitySpecialistEntity, Long> implements AutonomousCommunitySpecialistService {
    protected AutonomousCommunitySpecialistServiceImpl(GESCOMRepository<AutonomousCommunitySpecialistEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AutonomousCommunitySpecialistRepository autonomousCommunitySpecialistRepository;

    @Autowired
    private SpecialistConverter specialistConverter;


    @Override
    public List<SpecialistDTO> finByIdCampaign(Long idCampaign) {
        List<AutonomousCommunitySpecialistEntity> autonomousCommunitySpecialists = autonomousCommunitySpecialistRepository.findByIdCampaign(idCampaign);
        List<SpecialistDTO> specialistDTOS = new ArrayList<>();
        autonomousCommunitySpecialists.forEach(autonomousCommunitySpecialist -> {
            SpecialistEntity specialist = autonomousCommunitySpecialist.getSpecialist();
            specialistDTOS.add(specialistConverter.convertToModel(specialist));
        });
        Set<SpecialistDTO> set = new HashSet<>(specialistDTOS);
        return set.stream().toList();
    }
}
