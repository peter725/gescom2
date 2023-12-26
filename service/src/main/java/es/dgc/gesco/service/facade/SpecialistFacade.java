package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.specialist.converter.SpecialistConverter;
import es.dgc.gesco.model.modules.specialist.db.entity.Specialist;
import es.dgc.gesco.model.modules.specialist.dto.SpecialistDTO;
import es.dgc.gesco.service.service.SpecialistService;
import org.springframework.stereotype.Component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Component
public class SpecialistFacade {
    @Autowired
    private SpecialistService specialistService;

    @Autowired
    private SpecialistConverter specialistConverter;

    public Page<SpecialistDTO> getAllSpecialist(Pageable pageable) {
        Page<Specialist> specialistPage = specialistService.getAllByPage(pageable);
        return  loadSpecialistPageDto(specialistPage);
    }

    private Page<SpecialistDTO> loadSpecialistPageDto(Page<Specialist> specialistPage) {
        Page<SpecialistDTO> specialistDtoPage = specialistPage.map(proponent -> specialistConverter.convertSpecialistToDto(proponent));
        specialistDtoPage.forEach(specialistDTO -> {
            specialistDTO.setId(specialistDTO.getId());
            specialistDTO.setName(specialistDTO.getName());
        });
        return specialistDtoPage;
    }
}