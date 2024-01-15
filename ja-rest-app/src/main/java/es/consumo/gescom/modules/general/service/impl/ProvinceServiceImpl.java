package es.consumo.gescom.modules.general.service.impl;

import es.consumo.gescom.modules.general.model.entity.ProvinceEntity;
import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.commons.service.EntityReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author serikat
 */
@Service
public class ProvinceServiceImpl extends EntityReadService<ProvinceEntity, Long> {
    @Autowired
    public ProvinceServiceImpl(JJAARepository<ProvinceEntity, Long> repository) {
        super(repository);
    }
}
