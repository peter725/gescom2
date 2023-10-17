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
}
