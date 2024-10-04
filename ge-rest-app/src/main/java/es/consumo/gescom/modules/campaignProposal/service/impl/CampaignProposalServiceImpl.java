package es.consumo.gescom.modules.campaignProposal.service.impl;

import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.exception.AppException;
import es.consumo.gescom.modules.arbitration.model.dto.ChangeStatusDTO;
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

import es.consumo.gescom.modules.users.model.entity.UserEntity;
import es.consumo.gescom.modules.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class CampaignProposalServiceImpl extends EntityCrudService<CampaignProposalEntity, Long> implements CampaignProposalService {
    protected CampaignProposalServiceImpl(GESCOMRepository<CampaignProposalEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampaignProposalRepository campaignProposalRepository;

    @Autowired
    private AutonomousCommunityRepository autonmousCommunityRepository;

    @Autowired
    private CampaignTypeRepository campaignTypeRepository;

    @Autowired
    private CampaignProposalConverter campaignProposalConverter;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CampaignProposalDTO createCampaignProposal(CampaignProposalDTO campaignProposalDTO) {
        String username = getCurrentUsername();
        UserEntity userEntity = userRepository.findByUsername(username);

        campaignProposalDTO.setDate(LocalDate.now());
        campaignProposalDTO.setSent(true);
        //ojo cambiar al momento de estar activo el perfil de usuario aqui se debe guardar la comunidad autonoma de usuario logueado
        if (userEntity.getAutonomousCommunity() != null) campaignProposalDTO.setAutonomousCommunityId(userEntity.getAutonomousCommunity().getId());
        else campaignProposalDTO.setAutonomousCommunityId(autonmousCommunityRepository.findIdByName("DIRECCIÓN GENERAL DE CONSUMO"));
        CampaignProposalEntity campaignProposal =  campaignProposalConverter.convertToEntity(campaignProposalDTO);
        campaignProposalRepository.save(campaignProposal);

        return campaignProposalConverter.convertToModel(campaignProposal);
    }

    // Metodo para obtener el username del usuario autenticado
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new AppException("Unauthorized", null);
        }
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    @Override
    public Page<CampaignProposalDTO> findAllCampaignProposal(CriteriaWrapper<?> wrapper) {
    	CampaignProposalCriteria campaignProposalCriteria = (CampaignProposalCriteria) wrapper.getCriteria();
        if (campaignProposalCriteria.getSearch() != null) {
        	campaignProposalCriteria.setSearch(campaignProposalCriteria.getSearch().toUpperCase());
        }
        if (campaignProposalCriteria.getAutonomusCommunity() != null) {
        	campaignProposalCriteria.setAutonomusCommunity(campaignProposalCriteria.getAutonomusCommunity().toUpperCase());
        }
        if (campaignProposalCriteria.getApproach() != null) {
        	campaignProposalCriteria.setApproach(campaignProposalCriteria.getApproach().toUpperCase());
        }
        if (campaignProposalCriteria.getType() != null) {
        	campaignProposalCriteria.setType(campaignProposalCriteria.getType().toUpperCase());
        }
        if (campaignProposalCriteria.getState() == null || campaignProposalCriteria.getState().length == 0) {
        	campaignProposalCriteria.setState(new Integer[]{1});
        }
        
        String[] originalSort = campaignProposalCriteria.getSort();
        if (campaignProposalCriteria.getSort() != null && campaignProposalCriteria.getSort()[0].contains("year")) {
        	campaignProposalCriteria.setSort(new String[] {campaignProposalCriteria.getSort()[0].replace("year", "date")});
        }
        if (campaignProposalCriteria.getSort() != null && campaignProposalCriteria.getSort()[0].contains("type")) {
        	campaignProposalCriteria.setSort(new String[] {campaignProposalCriteria.getSort()[0].replace("type", "campaignTypeId")});
        }
        if (campaignProposalCriteria.getSort() != null && campaignProposalCriteria.getSort()[0].contains("autonomusCommunity")) {
        	campaignProposalCriteria.setSort(null);
        }
    	
        Page<CampaignProposalEntity> campaignProposalEntityPage = null;
        if (originalSort != null && originalSort[0].contains("autonomusCommunity")) {
        	if (originalSort[0].split(";")[1].equals("asc")) {
                campaignProposalEntityPage = campaignProposalRepository.findAllByCriteriaOrderByAutonomousCommunityAsc(campaignProposalCriteria, wrapper.getCriteria().toPageable());
        	} else {
                campaignProposalEntityPage = campaignProposalRepository.findAllByCriteriaOrderByAutonomousCommunityDesc(campaignProposalCriteria, wrapper.getCriteria().toPageable());
        	}
        } else {
            campaignProposalEntityPage = campaignProposalRepository.findAllByCriteria(campaignProposalCriteria, wrapper.getCriteria().toPageable());
        }
        
        Page<CampaignProposalDTO> campaignProposalDTOPage = campaignProposalEntityPage.map(campaignProposalConverter::convertToModel);
        campaignProposalDTOPage.forEach(campaignProposalDTO -> {
            Optional<AutonomousCommunityEntity> optionalAutonomousCommunityEntity = autonmousCommunityRepository.findById(campaignProposalDTO.getAutonomousCommunityId());
            if (optionalAutonomousCommunityEntity.isPresent()) {
                campaignProposalDTO.setAutonomousCommunityName(optionalAutonomousCommunityEntity.get().getName());
            }
            Optional<CampaignTypeEntity> optionalCampaignTypeEntity = campaignTypeRepository.findById(campaignProposalDTO.getCampaignTypeId());
            if (optionalCampaignTypeEntity.isPresent()) {
                campaignProposalDTO.setCampaignTypeName(optionalCampaignTypeEntity.get().getName());
            }
        });
        return campaignProposalDTOPage;
    }

    @Override
    public CampaignProposalDTO findCampaignProposalById(Long id) {
        CampaignProposalEntity campaignProposalEntity = repository.findById(id).orElse(null);
        CampaignProposalDTO campaignProposalDTO = campaignProposalConverter.convertToModel(campaignProposalEntity);
        AutonomousCommunityEntity autonomousCommunityEntity = autonmousCommunityRepository.findById(campaignProposalDTO.getAutonomousCommunityId()).orElse(null);
        CampaignTypeEntity campaignTypeEntity = campaignTypeRepository.findById(campaignProposalDTO.getCampaignTypeId()).orElse(null);
        campaignProposalDTO.setAutonomousCommunityName(autonomousCommunityEntity.getName());
        campaignProposalDTO.setCampaignTypeName(campaignTypeEntity.getName());
        campaignProposalDTO.setYear(campaignProposalEntity.getDate().getYear());
        return campaignProposalDTO;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CampaignProposalEntity update(CampaignProposalDTO payload) {
        CampaignProposalEntity campaignProposalEntity = repository.findById(payload.getId()).orElseThrow();
        payload.setSent(campaignProposalEntity.getSent());
        payload.setAutonomousCommunityId(campaignProposalEntity.getAutonomousCommunityId());
        payload.setUserId(campaignProposalEntity.getUserId());
        modelMapper.map( payload,campaignProposalEntity);
        repository.save(campaignProposalEntity);
        return campaignProposalEntity;
    }

}
