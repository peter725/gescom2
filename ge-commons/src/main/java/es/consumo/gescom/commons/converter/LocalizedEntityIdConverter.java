package es.consumo.gescom.commons.converter;

import es.consumo.gescom.commons.db.id.LocalizedEntityId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Transforma el ID formateado como string de las entidades localizadas en LocalizedEntityId durante el proceso
 * de DataBinding del request.
 * <p>
 * <p>
 * <a href="https://www.baeldung.com/spring-mvc-custom-data-binder">Más detalles</a>
 * <p>
 * <a href="https://www.baeldung.com/spring-mvc-custom-data-binder#binding-a-hierarchy-of-objects">
 * En el futuro puede que haga falta implementar varias formas para esta conversión</a>
 */
@Component
public class LocalizedEntityIdConverter implements Converter<String, LocalizedEntityId> {
    @Override
    public LocalizedEntityId convert(String source) {
        List<Long> list = FormattedLongIdConverter.decode(source);
        return new LocalizedEntityId(list.get(0), list.get(1));
    }
}
