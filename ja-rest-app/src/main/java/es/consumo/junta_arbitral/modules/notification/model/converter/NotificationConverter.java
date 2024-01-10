package es.consumo.junta_arbitral.modules.notification.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import es.consumo.junta_arbitral.commons.converter.SimpleDataConverter;
import es.consumo.junta_arbitral.modules.notification.model.dto.NotificationDTO;
import es.consumo.junta_arbitral.modules.notification.model.entity.NotificationEntity;

@Service
public class NotificationConverter extends SimpleDataConverter<NotificationEntity, NotificationDTO> {
    public NotificationConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
