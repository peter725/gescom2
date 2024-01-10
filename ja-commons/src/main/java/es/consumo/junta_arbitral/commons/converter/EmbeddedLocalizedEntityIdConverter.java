package es.consumo.junta_arbitral.commons.converter;

import es.consumo.junta_arbitral.commons.db.id.EmbeddableLocalizedEntityId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmbeddedLocalizedEntityIdConverter implements Converter<String, EmbeddableLocalizedEntityId> {
    @Override
    public EmbeddableLocalizedEntityId convert(String source) {
        List<Long> list = FormattedLongIdConverter.decode(source);
        return new EmbeddableLocalizedEntityId(list.get(0), list.get(1));
    }
}
