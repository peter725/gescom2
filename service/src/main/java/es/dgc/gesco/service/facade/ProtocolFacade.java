package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.protocol.converter.ProtocolConverter;
import es.dgc.gesco.model.modules.protocol.db.entity.Protocol;
import es.dgc.gesco.model.modules.protocol.dto.ProtocolDTO;
import es.dgc.gesco.service.service.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProtocolFacade {

    @Autowired
    private ProtocolService protocolService;

    @Autowired
    private ProtocolConverter protocolConverter;

    public Page<ProtocolDTO> getProtocolByNameOrCode(String protocol, String code, Pageable pageable) {
        Page<Protocol> protocolPage = protocolService.getProtocolByNameOrCode(protocol, code,pageable);

        Page<ProtocolDTO> protocolDTOPage =  protocolPage.map(protocol1 -> protocolConverter.convertProtocolToDto(protocol1));

        return  protocolDTOPage;
    }

}