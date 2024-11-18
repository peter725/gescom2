export type EnvironmentData = {
  /**
   * Nombre del entorno.
   */
  name: string;

  /**
   * Flag para especificar si se encuentra en un
   * entorno de producción.
   */
  production: boolean;

  /**
   * Segmento de la URL base aplicado en el lugar
   * de despliegue.
   */
  baseHref: string;

  /**
   * Definición de los servicios disponibles.
   */
  srv: Services;
};


/**
 * Parámetros de configuración del servicio.
 */
export type Services = {
  app: ServiceItem;
  auth: AuthItem;
};

/**
 * Configuración específica para el servicio
 */
export type ServiceItem = {
  /**
   * URL base del servicio definido.
   */
  host: string;
  /**
   * URL para la API pública del servicio.
   */
  api: string;
};

export type AuthItem = {
  /**
   * URL base del servicio definido.
   */
  host: string;
  /**
   * URL para la API pública del servicio.
   */
  api: string;
  /**
   * Username for Basic Auth
   */
  username: string;
  /**
   * Paswword for Basic Auth
   */
  password: string;
}
