package es.consumo.junta_arbitral.modules.general.service.impl;

import es.consumo.junta_arbitral.modules.general.model.entity.ProvinceEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.service.EntityReadService;
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
