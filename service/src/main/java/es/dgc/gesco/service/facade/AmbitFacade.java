package es.dgc.gesco.service.facade;


import es.dgc.gesco.model.modules.ambit.converter.AmbitConverter;
import es.dgc.gesco.model.modules.ambit.db.entity.Ambit;
import es.dgc.gesco.model.modules.ambit.dto.AmbitDTO;
import es.dgc.gesco.service.service.AmbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AmbitFacade {
    @Autowired
    private AmbitService ambitService;

    @Autowired
    private AmbitConverter ambitConverter;

    public Page<AmbitDTO> getAllAmbit(Pageable pageable) {
        Page<Ambit> ambitPage = ambitService.getAllByPage(pageable);

        return  loadAmbitPageDto(ambitPage);
    }

    private Page<AmbitDTO> loadAmbitPageDto(Page<Ambit> ambitPage) {
        Page<AmbitDTO> ambitDtoPage = ambitPage.map(ambit -> ambitConverter.convertAmbitToDto(ambit));
        ambitDtoPage.forEach(ambitDto -> {
            ambitDto.setId(ambitDto.getId());
            ambitDto.setAmbit(ambitDto.getAmbit());
        });
        return ambitDtoPage;
    }
}