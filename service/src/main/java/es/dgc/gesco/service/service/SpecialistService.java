package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.specialist.db.entity.Specialist;
import es.dgc.gesco.service.repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialistService {

    @Autowired
    private final SpecialistRepository specialistRepository;

    public Page<Specialist> getAllByPage(Pageable pageable) {
        Page<Specialist> specialistPage = specialistRepository.findAll(pageable);
        return specialistPage;
    }



}
