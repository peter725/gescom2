package es.consumo.gescom.modules.arbitration.model.constants;

public enum RequestStatus {
    CREATED,
    DRAFT,
    SIGNED,
    SENT,
    TERRITORIAL_COMPETITION,
    ASSIGNED,
    ADMISSION,
    INADMISSIBLE,
    ADMITTED,
    ARBITRATION_AGREEMENT,
    STARTED_PROCEDURE,
    ARCHIVED,
    VERIFIED,
    SIGNED_CORRECTION,
    CORRECT_FINISH,
    CORRECT
}
/**
 * -Creada
 * -Borrador
 * -Firmada(digital)
 * <p>
 * -Enviada
 * -CompetenciaTerritorial
 * -Asignada
 * <p>
 * -Admision
 * -Inadmitida
 * -Admitida
 * <p>
 * -ConvenioArbitral
 * -SiExisteConvenio
 * -NoExisteConvenio
 * <p>
 * -InicioProcedimiento
 * (aviso de 15 días pero usuario da click)
 * <p>
 * <p>
 * -AsignacionArbitros
 * -Audiencia
 * -Laudo (SentenciaFinal)
 * -NotificarLauda
 * ....
 * -Archivada
 */