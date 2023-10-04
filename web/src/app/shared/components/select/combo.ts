export type ComboValue = number | string | boolean | Record<string, any> | Date | undefined | null;

export const simplifyComboValue = (obj: ComboValue): string | number | boolean | undefined => {
  if (obj == null || Array.isArray(obj)) {
    return undefined;
  }
  if (typeof obj === 'string' || typeof obj === 'number' || typeof obj === 'boolean') {
    return obj;
  }
  if (obj instanceof Date) {
    return obj.getTime();
  }

  // Propiedades típicas que pueden identificar el objeto de una forma clara
  if (obj['id']) {
    return obj['id'];
  }

  if (obj['code']) {
    return obj['code'];
  }

  if (obj['name']) {
    return obj['name'];
  }

  if (obj['value']) {
    return obj['value'];
  }

  try {
    return JSON.stringify(obj);
  } catch (e) {
    return undefined;
  }
};

export const compareObjects = (a: ComboValue, b: ComboValue): boolean => simplifyComboValue(a) === simplifyComboValue(b);
