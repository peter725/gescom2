package es.consumo.gescom.modules.document.service;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;
import es.consumo.gescom.modules.document.model.criteria.DocumentCriteria;
import es.consumo.gescom.modules.document.model.entity.DocumentEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DocumentService extends CrudService<DocumentEntity, Long>{
    Page<DocumentEntity> findDocumentByCampaignId(CriteriaWrapper<DocumentCriteria> documentCriteriaCriteriaWrapper, Long idCampaign);

}
