package es.consumo.gescom.modules.ipr.service.impl;

import es.consumo.gescom.modules.campaign.model.converter.CampaignConverter;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.ipr.model.converter.IprConverter;
import es.consumo.gescom.modules.ipr.model.criteria.IprCriteria;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import es.consumo.gescom.modules.ipr.repository.IprRepository;
import es.consumo.gescom.modules.ipr.service.IprService;
import es.consumo.gescom.modules.iprQuestion.model.dto.IprQuestionDTO;
import es.consumo.gescom.modules.iprQuestion.model.entity.IprQuestionEntity;
import es.consumo.gescom.modules.iprQuestion.repository.IprQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class IprServiceImpl extends EntityCrudService<IprEntity, Long> implements IprService {
    protected IprServiceImpl(GESCOMRepository<IprEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private IprRepository iprRepository;

    @Autowired
    private IprConverter iprConverter;

    @Autowired
    private IprQuestionRepository iprQuestionRepository;

    @Override
    public Page<IprEntity.SimpleProjection> findAllIprById(CriteriaWrapper<IprCriteria> wrapper, Long id) {
        return ((IprRepository) repository).findAllIprById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public IprDTO createIPR(IprDTO payload) {
        IprEntity iprEntity =  iprConverter.convertToEntity(payload);
        IprEntity iprEntitySave = iprRepository.save(iprEntity);

        List<IprQuestionDTO> iprQuestionDTOList = payload.getIprQuestionDTOList();
        iprQuestionDTOList.forEach(iprQuestion -> {
            IprQuestionEntity iprQuestionEntity = new IprQuestionEntity();
            iprQuestionEntity.setCode(iprQuestion.getCode());
            iprQuestionEntity.setIprCode(iprQuestion.getIprCode());
            iprQuestionEntity.setOrderQuestion(iprQuestion.getOrderQuestion());
            iprQuestionEntity.setPercentageRespectTo(iprQuestion.getPercentageRespectTo());
            iprQuestionEntity.setFormula(iprQuestion.getFormula());
            iprQuestionEntity.setQuestion(iprQuestion.getQuestion());
            iprQuestionEntity.setIprId(iprEntitySave);
            iprQuestionEntity.setCreatedAt(LocalDateTime.now());

            iprQuestionRepository.save(iprQuestionEntity);
        });

        IprDTO iprDTONew = iprConverter.convertToModel(iprEntitySave);
        iprDTONew.setIprQuestionDTOList(iprQuestionDTOList);


        return iprDTONew;
    }

}
