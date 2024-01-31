package es.consumo.gescom.modules.campaignProposal.service.impl;

import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunity.repository.AutonomousCommunityRepository;
import es.consumo.gescom.modules.campaignProposal.model.converter.CampaignProposalConverter;
import es.consumo.gescom.modules.campaignProposal.model.criteria.CampaignProposalCriteria;
import es.consumo.gescom.modules.campaignProposal.model.dto.CampaignProposalDTO;
import es.consumo.gescom.modules.campaignProposal.model.entity.CampaignProposalEntity;
import es.consumo.gescom.modules.campaignProposal.repository.CampaignProposalRepository;
import es.consumo.gescom.modules.campaignProposal.service.CampaignProposalService;
import es.consumo.gescom.modules.campaignType.model.entity.CampaignTypeEntity;
import es.consumo.gescom.modules.campaignType.repository.CampaignTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
public class CampaignProposalServiceImpl extends EntityCrudService<CampaignProposalEntity, Long> implements CampaignProposalService {
    protected CampaignProposalServiceImpl(GESCOMRepository<CampaignProposalEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private CampaignProposalRepository campaignProposalRepository;

    @Autowired
    private AutonomousCommunityRepository autonmousCommunityRepository;

    @Autowired
    private CampaignTypeRepository campaignTypeRepository;

    @Autowired
    private CampaignProposalConverter campaignProposalConverter;


    @Override
    public CampaignProposalDTO createCampaignProposal(CampaignProposalDTO campaignProposalDTO) {
        campaignProposalDTO.setDate(LocalDate.now());
        campaignProposalDTO.setSent(true);
        //ojo cambiar al momento de estar activo el perfil de usuario aqui se debe guardar la comunidad autonoma de usuario logueado
        campaignProposalDTO.setAutonomousCommunityId(20L);
        CampaignProposalEntity campaignProposal =  campaignProposalConverter.convertToEntity(campaignProposalDTO);
        campaignProposalRepository.save(campaignProposal);

        return campaignProposalConverter.convertToModel(campaignProposal);
    }

    @Override
    public Page<CampaignProposalDTO> findAllCampaignProposal(CriteriaWrapper<?> wrapper) {
        Page<CampaignProposalEntity> campaignProposalEntityPage = repository.findAll(wrapper.getCriteria().toPageable());
        Page<CampaignProposalDTO> campaignProposalDTOPage = campaignProposalEntityPage.map(campaignProposalConverter::convertToModel);
        campaignProposalDTOPage.forEach(campaignProposalDTO -> {
            AutonomousCommunityEntity autonomousCommunityEntity = autonmousCommunityRepository.findById(campaignProposalDTO.getAutonomousCommunityId()).orElse(null);
            CampaignTypeEntity campaignTypeEntity = campaignTypeRepository.findById(campaignProposalDTO.getCampaignTypeId()).orElse(null);
            campaignProposalDTO.setAutonomousCommunityName(autonomousCommunityEntity.getName());
            campaignProposalDTO.setCampaignTypeName(campaignTypeEntity.getName());
        });


        return campaignProposalDTOPage;
    }

    @Override
    public Page<CampaignProposalEntity.SimpleProjection> findAllCampaignProposalById(CriteriaWrapper<CampaignProposalCriteria> wrapper, Long id) {
        return ((CampaignProposalRepository) repository).findAllCampaignProposalById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public Page<CampaignProposalEntity.SimpleProjection> findCampaignProposalByYear(CriteriaWrapper<CampaignProposalCriteria> wrapper, int year) {
        LocalDate localDateIni = LocalDate.ofYearDay(year, 1);
        LocalDate localDateFin = LocalDate.ofYearDay(year, LocalDate.of(year, 12, 31).getDayOfYear());

        return ((CampaignProposalRepository) repository).findCampaignProposalByYear(wrapper.getCriteria().toPageable(), localDateIni, localDateFin);
    }

    @Override
    public Page<CampaignProposalEntity.SimpleProjection> findListByCriteriaAutonomousCommunityId(CriteriaWrapper<CampaignProposalCriteria> wrapper, Long id) {
        return ((CampaignProposalRepository) repository).findListByCriteriaAutonomousCommunityId(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    @Transactional
    public CampaignProposalEntity switchStatus(ChangeStatusDTO changeStatusDTO, Long id) {
        CampaignProposalEntity entity = findById(id).orElseThrow();
        entity.setState(changeStatusDTO.getStatus());

        return campaignProposalRepository.save(entity);
    }

}
