package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;
import es.dgc.gesco.model.modules.proponent.dto.ProponentDto;
import es.dgc.gesco.model.specialist.converter.SpecialistConverter;
import es.dgc.gesco.model.specialist.db.entity.Specialist;
import es.dgc.gesco.model.specialist.dto.SpecialistDto;
import es.dgc.gesco.service.service.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class SpecialistFacade {
//    @Autowired
//    private SpecialistService specialistService;
//
//    @Autowired
//    private SpecialistConverter specialistConverter;
//
//    public Page<SpecialistDto> getAllSpecialist(Pageable pageable) {
////        Page<Specialist> specialistPage = specialistService.getAllByPage(pageable);
//
////        return  loadSpecialistPageDto(specialistPage);
//        return null;
//    }
//
//    private Page<SpecialistDto> loadSpecialistPageDto(Page<Specialist> specialistPage) {
//        Page<SpecialistDto> specialistDtoPage = specialistPage.map(proponent -> specialistConverter.convertSpecialistToDto(proponent));
//        specialistDtoPage.forEach(ambitDto -> {
//            ambitDto.setId(ambitDto.getId());
//            ambitDto.setSpecialist(ambitDto.getSpecialist());
//        });
//        return specialistDtoPage;
//    }
}