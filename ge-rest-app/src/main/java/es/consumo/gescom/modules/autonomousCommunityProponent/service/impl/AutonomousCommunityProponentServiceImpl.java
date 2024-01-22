package es.consumo.gescom.modules.autonomousCommunityProponent.service.impl;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.modules.autonomousCommunity.model.converter.AutonomousCommunityConverter;
import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.repository.AutonomousCommunityRepository;
import es.consumo.gescom.modules.autonomousCommunityProponent.model.entity.AutonomousCommunityProponentEntity;
import es.consumo.gescom.modules.autonomousCommunityProponent.repository.AutonomousCommunityProponentRepository;
import es.consumo.gescom.modules.autonomousCommunityProponent.service.AutonomousCommunityProponentService;
import es.consumo.gescom.modules.proponent.model.converter.ProponentConverter;
import es.consumo.gescom.modules.proponent.model.dto.ProponentDTO;
import es.consumo.gescom.modules.proponent.model.entity.ProponentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AutonomousCommunityProponentServiceImpl extends EntityCrudService<AutonomousCommunityProponentEntity, Long> implements AutonomousCommunityProponentService {
    protected AutonomousCommunityProponentServiceImpl(GESCOMRepository<AutonomousCommunityProponentEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private AutonomousCommunityProponentRepository autonomousCommunityProponentRepository;

    @Autowired
    private ProponentConverter proponentConverter;



    @Override
    public List<ProponentDTO> finByIdCampaign(Long idCampaign) {
        List<AutonomousCommunityProponentEntity> autonomousCommunityProponents = autonomousCommunityProponentRepository.finByIdCampaign(idCampaign);
        List<ProponentDTO> proponentDTOS = new ArrayList<>();
        autonomousCommunityProponents.forEach(autonomousCommunityProponent -> {
            ProponentEntity proponent = autonomousCommunityProponent.getProponent();
            proponentDTOS.add(proponentConverter.convertToModel(proponent));
        });
        Set<ProponentDTO> set = new HashSet<>(proponentDTOS);
        return set.stream().toList();
    }
}
