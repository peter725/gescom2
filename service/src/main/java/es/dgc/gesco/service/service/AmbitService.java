package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.ambit.db.entity.Ambit;
import es.dgc.gesco.model.modules.campaign.db.entity.Campaign;
import es.dgc.gesco.service.repository.AmbitRepository;
import es.dgc.gesco.service.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AmbitService {

    @Autowired
    private final AmbitRepository ambitRepository;

    public Page<Ambit> getAllByPage(Pageable pageable) {
        Page<Ambit> ambitPage = ambitRepository.findAll(pageable);
        return ambitPage;
    }



}