import { of, createStorage } from './factories';


describe('Create named route value', () => {
  it('Should create a valid named route', () => {
    const result = of({ key: 'home ', value: 'home' });
    expect(result).toBeTruthy();
  });

  it('Should sanitize source input', () => {
    const result = of({ key: 'home ', value: 'home' });
    const expected = { key: 'home', value: 'home' };
    expect(result).toStrictEqual(expected);
  });

  it('Should not create routes with a root forward slash ("/")', () => {
    const result = of({ key: 'home', value: '/home' });
    const expected = { key: 'home', value: 'home' };
    expect(result).toStrictEqual(expected);
  });

  it('Should not create if there is no key or value provided', () => {
    const result = () => of({ key: '', value: '' });
    expect(result).toThrow();
  });
});

describe('Create a storage with routes', () => {
  it('Should create a routes storage', () => {
    const result = createStorage([{ key: 'home', value: 'home' }]);
    expect(result).toBeTruthy();
  });

  it('Should not create an empty storage', () => {
    const result = () => createStorage([]);
    expect(result).toThrow();
  });
});
