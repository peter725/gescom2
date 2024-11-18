import { NamedRouteSource, NamedRoutesStorage } from './models';


/**
 * Creates a new {@link NamedRouteSource} and validates the given {@param source}
 */
export function of({ key, value }: NamedRouteSource): NamedRouteSource {
  const parsedKey = key?.trim();
  let parsedValue = value?.trim();
  if (!parsedKey || !parsedValue) {
    throw new Error(`Must provide a valid key-value pair. Provided data was: "key: ${key}", "value: ${value}"`);
  }
  if (parsedValue.startsWith('/')) {
    parsedValue = parsedValue.slice(1, parsedValue.length);
  }
  return {
    key: parsedKey,
    value: parsedValue
  };
}

/**
 * Creates a new {@link NamedRoutesStorage} instance with the provided routes.
 * @return NamedRoutesStorage instance
 * @throws Undefined routes error
 */
export function createStorage(routes: NamedRouteSource[]): NamedRoutesStorage {
  if (!routes?.length) {
    throw new Error('Undefined routes error');
  }
  const namedRoutes = new Map<string, string>();
  routes.forEach((route) => {
    const { key, value } = of(route);
    namedRoutes.set(key, value);
  });
  return namedRoutes;
}
