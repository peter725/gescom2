package es.consumo.junta_arbitral.modules.document.service.impl;

import es.consumo.junta_arbitral.modules.arbitration.model.constants.ClaimantType;
import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.document.model.criteria.DocumentTypeCriteria;
import es.consumo.junta_arbitral.modules.document.model.entity.DocumentTypeEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.dto.FilterCriteria;
import es.consumo.junta_arbitral.commons.service.EntityReadService;
import es.consumo.junta_arbitral.commons.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author serikat
 */
@Service
public class DocumentTypeServiceImpl extends EntityReadService<DocumentTypeEntity, Long> {

    private final ReadService<ArbitrationEntity, Long> arbitrationService;

    @Autowired
    public DocumentTypeServiceImpl(JJAARepository<DocumentTypeEntity, Long> repository,
                                   ReadService<ArbitrationEntity, Long> arbitrationService) {
        super(repository);
        this.arbitrationService = arbitrationService;
    }

    @Override
    protected Page<DocumentTypeEntity> findAllFromCriteria(FilterCriteria criteria) {
        DocumentTypeCriteria documentCriteria = (DocumentTypeCriteria) criteria;
        criteria.setSort(new String[]{"id;asc"});
        Page<DocumentTypeEntity> page = super.findAllFromCriteria(documentCriteria);
        if (documentCriteria.isRequired()) {
            ArbitrationEntity arbitration = arbitrationService.findById(documentCriteria.getArbitrationId()).orElseThrow();
            if (ClaimantType.OWN.equals(arbitration.getClaimant().getClaimantType())) {
                page = new PageImpl<>(
                        page.getContent().stream()
                                .filter(e -> e.getId() != 2L).collect(Collectors.toList()),
                        page.getPageable(), page.getTotalElements());
            }
        }
        return page;
    }
}
