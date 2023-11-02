import { NamedRoutePipe } from './named-route.pipe';
import { createStorage } from './factories';
import { NamedRoutes } from './named-routes';


const routes = createStorage([
  { key: 'phone', value: '/phone' },
  { key: 'phone-params', value: '/phone/:param' }
]);

describe('NamedRoutePipe', () => {
  const service = new NamedRoutes(routes);
  const pipe = new NamedRoutePipe(service);

  it('Should create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('Should return segments', () => {
    const result = pipe.transform('phone');
    const expected = ['/', 'phone'];
    expect(result).toStrictEqual(expected);
  });

  it('Should return segments with parsed params', () => {
    const result = pipe.transform('phone-params', { param: 42 });
    const expected = ['/', 'phone', '42'];
    expect(result).toStrictEqual(expected);
  });

  it('Should return a list of empty segments', () => {
    const result = pipe.transform('');
    const expected: string[] = [];
    expect(result).toStrictEqual(expected);
  });

});
