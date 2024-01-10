package es.consumo.junta_arbitral.commons.service;

import es.consumo.junta_arbitral.commons.db.entity.Language;

public interface LanguageService extends CrudService<Language, Long> {

    Language getNoLanguage();

    Language getCurrent();

    Language getLanguageOrGetDefault(Long id);

}
