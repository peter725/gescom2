import { ValidationErrors } from '@angular/forms';

export const NIF_TEST = /^(\d|[XYZ])\d{7}[A-Z]$/;

export const CIF_TEST = /^[ABCDEFGHJKLMNPQRSUVW]\d{7}[\dA-J]$/;

export const NIF_CHAR_SEQUENCE = 'TRWAGMYFPDXBNJZSQVHLCKE';

export const validNIF = (value: string): ValidationErrors | null => {
  console.log("entra a validNIF");
  const matchDigits = value.match(/\d+/);
  if (value.length != 9 || !matchDigits) {
    console.log("entra aqui != 9 || !matchDigits")
    return { invalidNif: true };
  }

  const rawDigits = matchDigits.join('');

  const firstChar = value[0];
  const prefix = firstChar !== 'Z'
    ? firstChar !== 'Y'
      ? 0 // First char is number
      : 1 // First char is Y
    : 2; // First char is Z

  const digits = prefix + rawDigits;

  const module = (+digits) % 23;
  console.log("module",module);
  const lastChar = value[8];
  console.log("lastchar", lastChar);
  if (lastChar === NIF_CHAR_SEQUENCE[module]) {
    console.log("entra a lastChar")
    return null;
  }

  return { invalidNif: true };
};


export const validCIF = (value: string): ValidationErrors | null => {
  let sum = 0;
  for (let i = 1; i < 8; ++i) {
    const num = (+value[i]) << (i % 2);
    sum += (num / 10) + num % 10;
  }

  const module = (10 - sum) % 10;

  const firstChar = value[0];
  const secondChar = value[1];
  const thirdChar = value[2];
  const lastChar = value[8];

  if (
    ((/[KLMNPQRSW]/.test(firstChar) || (secondChar + thirdChar) == '00') && lastChar === 'JABCDEFGHI'[module])
    || (/[ABEH]/.test(firstChar) && (+lastChar) === module)
    || (/[CDFGJUV]/.test(firstChar) && (lastChar === 'JABCDEFGHI'[module] || (+lastChar) === module))
  ) {
    return null;
  }

  return { invalidNif: true };
};
