package es.consumo.gescom.jwt.rest.auth.jwt.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAmount;
import java.util.Date;

/**
 * Proporciona una forma sencilla de obtener las unidades temporales
 * necesarias para crear y validar un JWT.
 *
 * @author serikat
 */
public class JwtTime {

	private final LocalDateTime time;

	public JwtTime(LocalDateTime time) {
		this.time = time;
	}

	/**
	 * Inicializa el tiempo en este mismo instante.
	 */
	public static JwtTime now() {
		return new JwtTime(currentDT());
	}

	/**
	 * Inicializa el tiempo y le suma el tiempo indicado.
	 */
	public static JwtTime after(TemporalAmount time) {
		return new JwtTime(currentDT().plus(time));
	}

	public Instant toInstant() {
		return this.time.toInstant(ZoneOffset.UTC);
	}

	public Date toDate() {
		return Date.from(this.toInstant());
	}

	public Long timestamp() {
		return this.toInstant().getLong(ChronoField.INSTANT_SECONDS);
	}

	private static LocalDateTime currentDT() {
		return LocalDateTime.now(ZoneId.from(ZoneOffset.UTC));
	}

}
