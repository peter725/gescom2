package es.dgc.gesco.model.commom.validation.validator;

import es.dgc.gesco.model.commom.validation.constraints.NIF;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class NifValidator implements ConstraintValidator<NIF, String> {

	public static class Patterns {
		
		
		private Patterns() {
			// Auto-generated constructor stub
		}
		/**
		 * Tabla de caracteres de control del NIF.
		 */
		public static final String CONTROL_LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

		/**
		 * Expresión para validar los NIT antiguos
		 */
		public static final String OLD_NIF_REGEX = "[Nn]\\d{7}[a-zA-Z0-9]"; // Documento antiguo
		/**
		 * Patrón para identificar los NIT antiguos.
		 */
		public static final Pattern OLD_NIF_DOC = Pattern.compile(OLD_NIF_REGEX);

		/**
		 * Expresión regular para identificar potenciales DNI.
		 */
		public static final String POTENTIAL_DNI_REGEX = "^\\d.*";
		/**
		 * Expresión regular para identificar DNI.
		 */
		public static final String DNI_REGEX = "^\\d{8}[A-Z&&[^O]]$";
		/**
		 * Patrón para identificar potenciales DNI.
		 */
		public static final Pattern POTENTIAL_DNI_DOC = Pattern.compile(POTENTIAL_DNI_REGEX);
		/**
		 * Patrón para identificar DNI.
		 */
		public static final Pattern DNI_DOC = Pattern.compile(DNI_REGEX);

		/**
		 * Expresión regular para identificar potenciales NIE.
		 */
		public static final String POTENTIAL_NIE_REGEX = "^[K-MX-Z].*";
		/**
		 * Expresión regular para identificar NIE.
		 */
		public static final String NIE_REGEX = "^[X-Z]\\d{7}[A-Z&&[^O]]$";
		/**
		 * Patrón para identificar potenciales NIE.
		 */
		public static final Pattern POTENTIAL_NIE_DOC = Pattern.compile(POTENTIAL_NIE_REGEX);
		/**
		 * Patrón para los NIE.
		 */
		public static final Pattern NIE_DOC = Pattern.compile(NIE_REGEX);

		/**
		 * Expresión regular para la identificación de menores de 14
		 * años españoles y residentes en España que no tienen DNI.
		 */
		public static final String MINOR_REGEX = "^K\\d{7}[A-Z&&[^O]]$";
		/**
		 * Patrón para los documentos de identificación de menores.
		 */
		public static final Pattern MINOR_DOC = Pattern.compile(MINOR_REGEX);

		/**
		 * Expresión regular para identificar NIE de españoles residentes
		 * en el extranjero que no tienen DNI.
		 */
		public static final String EXPAT_WITHOUT_DNI_REGEX = "^L\\d{7}[A-Z&&[^O]]$";
		/**
		 * Patrón para el NIE de españoles residentes en el extranjero que no tienen DNI.
		 */
		public static final Pattern EXPAT_WITHOUT_DNI_DOC = Pattern.compile(EXPAT_WITHOUT_DNI_REGEX);

		/**
		 * Expresión regular para la identificación de extranjeros que no
		 * disponen de NIE transitoria o definitivamente.
		 */
		public static final String FOREIGN_WITHOUT_NIE_REGEX = "^M\\d{7}[A-Z&&[^O]]$";
		public static final Pattern FOREIGN_WITHOUT_NIE_DOC = Pattern.compile(FOREIGN_WITHOUT_NIE_REGEX);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(NifValidator.class);

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		LOGGER.trace("Attempting NIF validation");
		//Implement validation
		LOGGER.trace("Completed NIF validation");
		return true;
	}
}
