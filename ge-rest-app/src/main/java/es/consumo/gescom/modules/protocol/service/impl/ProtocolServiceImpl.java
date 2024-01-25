package es.consumo.gescom.modules.protocol.service.impl;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.protocol.model.criteria.ProtocolCriteria;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.modules.protocol.repository.ProtocolRepository;
import es.consumo.gescom.modules.protocol.service.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;


@Service
public class ProtocolServiceImpl extends EntityCrudService<ProtocolEntity, Long> implements ProtocolService {
    protected ProtocolServiceImpl(GESCOMRepository<ProtocolEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProtocolRepository protocolRepository;

    @Override
    public Page<ProtocolEntity> getProtocolByNameOrCode(CriteriaWrapper<ProtocolCriteria> wrapper, String protocol, String code) {
        return ((ProtocolRepository) repository).getProtocolByNameOrCode(wrapper.getCriteria().toPageable(), protocol, code);

    }

    @Override
    public Page<ProtocolEntity> findProtocolByCampaignId(CriteriaWrapper<ProtocolCriteria> wrapper, Long idCampaign) {
        return ((ProtocolRepository) repository).findProtocolByCampaignId(wrapper.getCriteria().toPageable(), idCampaign);
    }
}
