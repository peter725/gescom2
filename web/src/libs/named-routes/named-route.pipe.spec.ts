import { NamedRoutePipe } from './named-route.pipe';
import { createStorage } from './factories';
import { NamedRoutes } from './named-routes';


const routes = createStorage([
  { key: 'home', value: '/home' },
  { key: 'home-params', value: '/home/:param' }
]);

describe('NamedRoutePipe', () => {
  const service = new NamedRoutes(routes);
  const pipe = new NamedRoutePipe(service);

  it('Should create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('Should return segments', () => {
    const result = pipe.transform('home');
    const expected = ['/', 'home'];
    expect(result).toStrictEqual(expected);
  });

  it('Should return segments with parsed params', () => {
    const result = pipe.transform('home-params', { param: 42 });
    const expected = ['/', 'home', '42'];
    expect(result).toStrictEqual(expected);
  });

  it('Should return a list of empty segments', () => {
    const result = pipe.transform('');
    const expected: string[] = [];
    expect(result).toStrictEqual(expected);
  });

});
