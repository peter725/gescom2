package es.consumo.junta_arbitral.commons.service.impl;

import es.consumo.junta_arbitral.commons.config.LocaleConfig;
import es.consumo.junta_arbitral.commons.db.entity.Language;
import es.consumo.junta_arbitral.commons.db.repository.LanguageRepository;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;
import es.consumo.junta_arbitral.commons.service.LanguageService;
import es.consumo.junta_arbitral.commons.exception.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author serikat
 */
@Service
public class LanguageServiceImpl
        extends EntityCrudService<Language, Long>
        implements LanguageService {

    public LanguageServiceImpl(JJAARepository<Language, Long> repository) {
        super(repository);
    }

    @Override
    public Language getNoLanguage() {
        return this.repository.findById(Language.NO_LANGUAGE).orElseThrow(NotFoundException::new);
    }

    @Override
    public Language getCurrent() {
        String isoCode = LocaleContextHolder.getLocale().getLanguage();
        if (StringUtils.isBlank(isoCode)) {
            isoCode = LocaleConfig.DEFAULT_LOCALE.getLanguage();
        }
        return ((LanguageRepository) repository).findByIsoCode(isoCode)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Language getLanguageOrGetDefault(Long id) {
        Optional<Language> optional = Optional.empty();
        if (id != null) {
            optional = repository.findById(id);
        }

        if (optional.isEmpty()) {
            String isoCode = LocaleContextHolder.getLocale().getLanguage();
            optional = ((LanguageRepository) repository).findByIsoCode(isoCode);
        }

        return optional.orElseThrow(NotFoundException::new);
    }

    @Override
    protected Language performUpdate(Long id, Language payload) {
        Language entity = repository.findById(id).orElseThrow(NotFoundException::new);
        entity.update(payload);
        return converter.convertToModel(repository.save(entity));
    }
}
