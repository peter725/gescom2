package es.consumo.gescom.commons.service;

import es.consumo.gescom.commons.db.entity.Language;

public interface LanguageService extends CrudService<Language, Long> {

    Language getNoLanguage();

    Language getCurrent();

    Language getLanguageOrGetDefault(Long id);

}
