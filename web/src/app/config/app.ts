import { Version } from '@angular/core';
import { environment } from '@env';

const getVersion = () => {
  let version = '0.0.0';
  if (environment.name) {
    version += '-' + environment.name;
  }
  return version;
};

export type AgencyInfo = {
  fullName: string;
  shortName?: string;
  webUrl: string;
  iconColor: string;
  iconNegative: string;
  iconPositive: string;
};

export const appName = 'Gesco';

export const version = new Version(getVersion());

export const isDevEnvironment = !environment.production;

export const government: AgencyInfo = {
  fullName: 'Gobierno de España',
  webUrl: 'https://www.lamoncloa.gob.es',
  iconColor: './assets/images/logos/logo-gob-esp.png',
  iconPositive: '',
  iconNegative: '',
};

export const aesan: AgencyInfo = {
  fullName: 'Agencia española de seguridad alimentaria y nutrición',
  shortName: 'AESAN',
  webUrl: 'https://www.aesan.gob.es/AECOSAN/web/home/aecosan_inicio.htm',
  iconColor: './assets/images/logos/logo_aesan_color.svg',
  iconPositive: './assets/images/logos/logo_aesan_positive.svg',
  iconNegative: './assets/images/logos/logo_aesan_negative.svg',
};

export const ministry: AgencyInfo = {
  fullName: 'Ministerio de Consumo',
  webUrl: 'https://www.consumo.gob.es/',
  iconColor: './assets/images/logos/logo-consumo.png',
  iconPositive: '',
  iconNegative: '',
};

export const PRTR: AgencyInfo = {
  fullName: 'Plan de Recuperación, Transformación y Resiliencia',
  shortName: 'PRTR',
  webUrl: 'https://planderecuperacion.gob.es/',
  iconColor: './assets/images/logos/logo_prtr_color.png',
  iconPositive: './assets/images/logos/logo_prtr_negative.png',
  iconNegative: './assets/images/logos/logo_prtr_positive.png',
};

export const dgc = {
  name: 'Dirección General de Consumo',
  shortName: 'DGC',
  web: 'https://www.consumo.gob.es/',
  icon: './assets/images/logos/logogescan.jpg',
};

export const appOrigin = window.location.origin + environment.baseHref;

const { auth, app } = environment.srv;
export const gescoAppAPI = {
  srvPath: app.host,
  apiPath: app.host + app.api,
};

export const gescoAuthAPI = {
  srvPath: 'auth.host',
  apiPath: 'auth.host + auth.api',
};
