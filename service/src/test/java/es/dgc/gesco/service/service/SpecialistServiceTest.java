package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.specialist.db.entity.Specialist;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import es.dgc.gesco.service.util.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Import(TestFactory.class)
public class SpecialistServiceTest extends AbstractGenericTest {

    @Autowired
    SpecialistService specialistService;

    @Test
    public void getAllTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Specialist> specialistPage = specialistService.getAllByPage(pageable);
    }
}