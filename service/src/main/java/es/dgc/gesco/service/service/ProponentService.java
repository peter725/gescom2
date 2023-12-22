package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.ambit.db.entity.Ambit;
import es.dgc.gesco.model.modules.proponent.db.entity.Proponent;
import es.dgc.gesco.service.repository.AmbitRepository;
import es.dgc.gesco.service.repository.ProponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProponentService {

    @Autowired
    private final ProponentRepository proponentRepository;


    public Proponent save(Proponent proponent) {
        return proponentRepository.save(proponent);
    }

    public Page<Proponent> getAllByPage(Pageable pageable) {
        Page<Proponent> proponentPage = proponentRepository.findAll(pageable);
        return proponentPage;
    }



}