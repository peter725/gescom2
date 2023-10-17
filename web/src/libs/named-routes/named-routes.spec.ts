import { NamedRoutes } from './named-routes';
import { TestBed, waitForAsync } from '@angular/core/testing';
import { createStorage } from './factories';
import { APP_ROUTES } from './tokens';


const routes = createStorage([
  { key: 'home', value: 'home' },
  { key: 'params', value: 'route/:param' }
]);

describe('NamedRoutes', () => {
  let service: NamedRoutes;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      providers: [
        { provide: APP_ROUTES, useValue: routes },
        NamedRoutes
      ]
    });
    service = TestBed.inject(NamedRoutes);
  }));

  it('Should create an instance', () => {
    expect(service).toBeTruthy();
  });

  it('Should return a route', () => {
    const result = service.getRoute('home');
    const expected = ['/', 'home'];
    expect(expected).toStrictEqual(result);
  });

  it('Should return a route with replaced parameters', () => {
    const result = service.getRoute('params', { param: 42 });
    const expected = ['/', 'route', '42'];
    expect(expected).toStrictEqual(result);
  });

  it('Should throw an error if a route does not exist', () => {
    const nested = () => service.getRoute('unknown');
    const expected = new Error('"unknown" is not registered as a key');
    expect(nested).toThrowError(expected);
  });
});
