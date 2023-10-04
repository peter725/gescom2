import { HttpErrorResponse } from '@angular/common/http';
import { ErrorCodes } from './error-codes';


export interface ErrorDetails<T = unknown> {
  /**
   * Fecha y Hora cuando ha ocurrido el error en formato UNIX timestamp
   */
  timestamp: number;
  /**
   * Código público del error que ha ocurrido
   */
  code: string;
  /**
   * Descripción corta del error
   */
  error: string;
  /**
   * Mensaje del error
   */
  message: string;
  /**
   * Datos adicionales relevantes para el error.
   * No incluir datos sensibles.
   */
  details: T;
}

export interface ValidationErrorDetails extends ErrorDetails {
  code: 'ValidationError',
  details: {
    field: string;
    message: string;
  }[];
}

export const UNKNOWN_ERROR: ErrorDetails = {
  timestamp: -1,
  code: ErrorCodes.UNKNOWN,
  error: 'Unknown error',
  message: '',
  details: undefined,
};

export const isErrorDetails = (e: any): e is ErrorDetails => {
  if (e == null) {
    return false;
  }

  const requiredProps = ['timestamp', 'code', 'error'];
  const keys = typeof e === 'object' ? Object.keys(e) : ['none'];
  return requiredProps.every(prop => keys.includes(prop) && e[prop] != null);
};

export const isValidationDetails = (e: any): e is ValidationErrorDetails =>
  isErrorDetails(e) && e.code === ErrorCodes.VALIDATION && Array.isArray(e.details)
;

export class AppError {
  static parse(src: unknown): ErrorDetails {
    const timestamp = Date.now();
    if (src == null) {
      return { ...UNKNOWN_ERROR, timestamp };
    }

    const err = src instanceof HttpErrorResponse ? src.error : src;

    if (isErrorDetails(err)) {
      return err;
    }

    if (err instanceof Error) {
      return {
        timestamp,
        code: err.name,
        message: err.message,
        error: err.message,
        details: undefined,
      };
    }

    return {
      ...UNKNOWN_ERROR,
      timestamp,
      error: err.toString(),
    };
  }
}
