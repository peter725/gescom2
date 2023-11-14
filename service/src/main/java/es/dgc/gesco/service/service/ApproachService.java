package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.approach.converter.ApproachConverter;
import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.approach.dto.ApproachDto;
import es.dgc.gesco.service.repository.ApproachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApproachService {

    @Autowired
    private final ApproachRepository approachRepository;

    @Autowired
    private ApproachConverter approachConverter;

    public Approach saveApproach(final Approach approach){

        Approach newApproach = approachRepository.save(approach);
        return newApproach;
    }

    public List<Approach> findAllApproach(){

        List<Approach> approaches = approachRepository.findAll();
        return approaches;
    }

    public Approach getApproachById(Long id){

        Optional<Approach> approach = approachRepository.findById(id);
        return approach.get();
    }

    public ApproachDto loadApproachDto(Approach approach) {
        ApproachDto approachDto = approachConverter.convertProposalToDto(approach);
        return approachDto;
    }

    public Approach loadApproach(ApproachDto approachDto) {
        Approach approach = approachConverter.convertDtoToUsuer(approachDto);
        return approach;
    }
}