import {
    AppQuery,
    AppQuerySource,
    FORMATTED_QUERY,
    HttpParamsSource,
    OPERATION_SEPARATOR,
    OperationMode
  } from '@libs/commons';
   
   
  type QueryOperation = {
    key: string;
    value: HttpParamsSource;
  };
   
  export class TulsaQuery implements AppQuery {
    constructor(private source: AppQuerySource) {
    }
   
    // Un-formatted raw values
    getSource() {
      const copy = { ...this.source };
      Object.entries(copy).forEach(([key, value]) => {
        const probe: unknown = value;
        if (probe instanceof Date) {
          copy[key] = probe.toISOString();
        }
      });
      return copy;
    }
   
    // Formatted values to be used as HttpParams
    toObject(): Record<string, HttpParamsSource> {
      return Object.entries(this.source || {}).reduce((acc, [key, value]) => {
        if (!key || value == null) return acc;
   
        let query = this.compileFormattedQuery(key, value);
        if (query == null) query = this.compileDateQuery(key, value);
        if (query == null) query = this.compileSimpleValue(key, value);
   
        if (query == null) return acc;
   
        acc[query.key] = query.value;
        return acc;
      }, {} as Record<string, HttpParamsSource>);
    }
   
    private compileSimpleValue(key: string, value: unknown): QueryOperation | null {
      if (!key || value == null) return null;
   
      if (Array.isArray(value)) {
        return {
          key,
          value: value as HttpParamsSource,
        };
      }
   
      const type = typeof value;
      const allowedTypes = ['string', 'number', 'boolean', 'object'];
      if (!allowedTypes.includes(type)) return null;
   
      const copy = value as (string | number | boolean | object);
      return {
        key,
        value: copy.toString().trim(),
      };
    }
   
    private compileFormattedQuery(key: string, value: unknown): QueryOperation | null {
      if (value == null || typeof value !== 'string' || !FORMATTED_QUERY.test(value)) return null;
   
      const parts = value.split(OPERATION_SEPARATOR);
      let query = '';
      let operationCode = '';
      if (parts.length > 3) {
        operationCode = parts.pop() || '';
        query = parts.join('');
      } else if (parts.length === 2) {
        query = parts[0];
        operationCode = parts[1];
      }
      // En caso de no tener la longitud necesaria,
      // no asignamos el valor de búsqueda
      let nextValue = query.trim();
   
      if (!value) return null;
   
      let nextKey = (key + '').trim();
      const operation = (operationCode.trim()) as OperationMode;
   
      if (operation) {
        switch (operation.trim()) {
          case OperationMode.CONTAINS:
            nextValue = `%${ nextValue }%`;
            break;
          case OperationMode.NOT_EQUALS:
            nextKey = `${ key }${ operation.toUpperCase() }`;
            nextValue = `%${ nextValue }%`;
            break;
          case OperationMode.STARTS_WITH:
            nextValue = `${ nextValue }%`;
            break;
          case OperationMode.ENDS_WITH:
            nextValue = `%${ nextValue }`;
            break;
        }
      }
   
      return {
        key: nextKey,
        value: nextValue,
      };
    }
   
    private compileDateQuery(key: string, value: unknown): QueryOperation | null {
      if (!key || !value) return null;
   
      let nextValue: string | undefined;
   
      if (value instanceof Date) {
        nextValue = value.toISOString();
      } else if (typeof value === 'string') {
        // Comprobamos si el valor es una fecha formateada como string
        // En caso de que el valor es un número dentro de un string, lo descartamos
        const intProbe = parseInt(value);
   
        const floatProbe = parseFloat(value);
        const valueIsNumeric = !isNaN(intProbe) || !isNaN(floatProbe);
        if (valueIsNumeric) return null;
   
        const dateProbe = Date.parse(value);
        if (!isNaN(dateProbe)) nextValue = new Date(dateProbe).toISOString();
   
      }
   
      if (nextValue == null) return null;
   
      return {
        key: key.trim(),
        value: nextValue,
      };
    }
   
  }