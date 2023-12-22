package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.approach.converter.ApproachConverter;
import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.approach.dto.ApproachDTO;
import es.dgc.gesco.service.repository.ApproachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Approach changeStateApproach(final Long id, final Integer state){

        Approach approach = getApproachById(id);
        approach.setState(state);
        approach.setSent(true);
        return approachRepository.save(approach);
    }

    public List<Approach> findAllApproach(){

        List<Approach> approaches = approachRepository.findAll();
        return approaches;
    }

    public Approach getApproachById(Long id){

        Optional<Approach> approach = approachRepository.findById(id);
        return approach.get();
    }

    public ApproachDTO loadApproachDto(Approach approach) {
        ApproachDTO approachDto = approachConverter.convertApproachToDto(approach);
        return approachDto;
    }

    public Approach loadApproach(ApproachDTO approachDto) {
        Approach approach = approachConverter.convertDtoToUsuer(approachDto);
        return approach;
    }

    public Page<Approach> getAllByPage(Pageable pageable) {
        Page<Approach> approachPage = approachRepository.findAll(pageable);
        return approachPage;
    }

    public Page<Approach> getApproachByAutonomousCommunityId(Long id, Pageable pageable) {
        Page<Approach> approachPage = approachRepository.getApproachByAutonomousCommunityId(id, pageable);
        return approachPage;
    }

    public Page<Approach> getApproachByDate(LocalDate localDateIni, LocalDate localDateFin, Pageable pageable) {
        Page<Approach> pageApproach = approachRepository.getApproachByDate(localDateIni, localDateFin, pageable);
        return pageApproach;
    }

}