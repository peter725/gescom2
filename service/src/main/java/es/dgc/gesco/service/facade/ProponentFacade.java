package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.proponent.converter.ProponentConverter;
import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;

import es.dgc.gesco.model.modules.proponent.dto.ProponentDto;
import es.dgc.gesco.service.service.ProponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProponentFacade {
    @Autowired
    private ProponentService proponentService;

    @Autowired
    private ProponentConverter proponentConverter;

    public Page<ProponentDto> getAllProponent(Pageable pageable) {
        Page<Proponent> proponentPage = proponentService.getAllByPage(pageable);

        return  loadAmbitPageDto(proponentPage);
    }

    private Page<ProponentDto> loadAmbitPageDto(Page<Proponent> proponentPage) {
        Page<ProponentDto> proponentDtoPage = proponentPage.map(proponent -> proponentConverter.convertProponentToDto(proponent));
        proponentDtoPage.forEach(ambitDto -> {
            ambitDto.setId(ambitDto.getId());
            ambitDto.setProponent(ambitDto.getProponent());
        });
        return proponentDtoPage;
    }
}