import { Version } from '@angular/core';
import { environment } from '@env';

const getVersion = () => {
  let version = '0.0.0';
  if (environment.name) {
    version += '-' + environment.name;
  }
  return version;
};

export const appName = 'Gesco';

export const version = new Version(getVersion());

export const isDevEnvironment = !environment.production;

export const government = {
  name: 'Gobierno de España',
  web: 'https://www.lamoncloa.gob.es',
  icon: './assets/images/logos/logo-gob-esp.png',
};

export const aesan = {
  name: 'Agencia española de seguridad alimentaria y nutrición',
  shortName: 'AESAN',
  web: 'https://www.aesan.gob.es/AECOSAN/web/home/aecosan_inicio.htm',
  icon: './assets/images/logos/logo-aesan.jpg',
};

export const dgc = {
  name: 'Dirección General de Consumo',
  shortName: 'DGC',
  web: 'https://www.consumo.gob.es/',
  icon: './assets/images/logos/logogescan.jpg',
};

export const ministry = {
  name: 'Ministerio de Consumo',
  web: 'https://www.consumo.gob.es/',
  icon: './assets/images/logos/logo-consumo.png',
};

export const appOrigin = window.location.origin + environment.baseHref;

// const { auth, app } = environment.srv;
export const gescoAppAPI = {
  srvPath: 'app.host',
  apiPath: 'app.host + app.api',
};

export const gescoAuthAPI = {
  srvPath: 'auth.host',
  apiPath: 'auth.host + auth.api',
};
