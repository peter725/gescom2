package es.dgc.gesco.service.facade;

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