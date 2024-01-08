package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.protocol.db.entity.Protocol;
import es.dgc.gesco.service.repository.ProtocolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProtocolService {


    private final ProtocolRepository protocolRepository;

    public Page<Protocol> getProtocolByNameOrCode(String protocol, String code, Pageable pageable) {

        Page<Protocol> protocolPage = protocolRepository.getProtocolByNameOrCode(protocol, code, pageable);
        return protocolPage;
    }
}