export const extractModelPath = (obj: unknown, path: string): string => {
  if (obj == null) {
    return '';
  }

  if (typeof obj === 'string' || typeof obj === 'number' || typeof obj === 'boolean') {
    return `${ obj }`;
  }

  if (typeof obj !== 'object') {
    return '';
  }

  if (Array.isArray(obj)) {
    return obj.map(o => extractModelPath(o, path)).join(', ');
  }

  const paths = path.split('.');
  if (!paths.length) {
    return '';
  }

  if (obj instanceof Date) {
    return `${ obj.getTime() }`;
  }

  if (paths.length === 1) {
    const property = paths[0];
    return extractModelPath((obj as Record<string, unknown>)[property], '');
  }

  const property = paths[0];
  paths.shift();
  path = paths.join('.');
  Object.getOwnPropertyNames(property).includes(property);
  if (property in obj) {
    return extractModelPath((obj as Record<string, unknown>)[property], path);
  }
  return '';
};
